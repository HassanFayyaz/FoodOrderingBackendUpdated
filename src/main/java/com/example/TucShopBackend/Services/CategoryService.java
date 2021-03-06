package com.example.TucShopBackend.Services;

import com.example.TucShopBackend.Commons.ApiResponse;
import com.example.TucShopBackend.Commons.CustomConstants;
import com.example.TucShopBackend.Commons.Status;
import com.example.TucShopBackend.DTO.CategoryDTO;
import com.example.TucShopBackend.Models.*;
import com.example.TucShopBackend.Models.Menu;
import com.example.TucShopBackend.Repositories.CategoryRepository;
import com.example.TucShopBackend.Repositories.MenuRepository;
import com.example.TucShopBackend.Repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductsService productsService;

    @Autowired
    ProductsRepository productsRepository;

    @Autowired
    MenuRepository menuRepository;

    @Value("${category.image.url}")
    String categoryImageUrl;

    @Value("${spring.profiles.active}")
    String profile;

    //serverfile.path
//    @Value("${serverfile.path}")
//    String serverFilePath;

    @Autowired
    private CloudinaryService cloudinaryService;

    public ApiResponse  postCategory(CategoryDTO categoryDTO){
        System.out.println("========================"+profile+"=======================");

        List<Category> allCategory= categoryRepository.findAll();
        if(allCategory.isEmpty()){
            String folderPath = CustomConstants.SERVER_PATH+"//"+"serverFiles//"+"//";
            File folder = new File(folderPath);
            deleteDirectory(folder);

        }
        Category categoryName = categoryRepository.findCategoriesByName(categoryDTO.getName());
        if(categoryName!= null){
            if(!categoryName.getActive())
            {
                categoryName.setActive(true);
                categoryRepository.save(categoryName);
                return new ApiResponse(Status.Status_Ok,CustomConstants.CAT_POSTED,categoryName);
            }
            return new ApiResponse(Status.Status_DUPLICATE, CustomConstants.CAT_DUPLICATE,null);
        }
        else{
            switch (profile){
                case CustomConstants.DEV:
                    String unique = String.valueOf(new Timestamp(System.currentTimeMillis()).getTime());
                    if(saveCategoryImage(categoryDTO.getImage(),categoryDTO.getName(),unique)){

                        Optional<Menu> menuOptional = menuRepository.findByRestaurantId(categoryDTO.getRestaurant_id());
                        if(menuOptional!=null) {
                            Menu menu = menuOptional.get();
                            Category category = new Category();
                            category.setImage(categoryImageUrl + categoryDTO.getName() + "/" + unique + categoryDTO.getImage().getOriginalFilename());
                            category.setName(categoryDTO.getName());
                            category.setMenu(menu);
//                            if (categoryDTO.getParentID() != null) {
//                                category.setParentID(categoryDTO.getParentID());
//                            }

                            category.setActive(true);
                            categoryRepository.save(category);
                            return new ApiResponse(Status.Status_Ok, CustomConstants.CAT_POSTED, category);
                        }
                    }

                break;

                case CustomConstants.PROD:
                    try {
                        Map map =  cloudinaryService.upload(categoryDTO.getImage());
                        Category category = new Category();
                        category.setImage(map.get("url").toString());
                        category.setName(categoryDTO.getName());
                        category.setActive(true);
                        categoryRepository.save(category);
                        return new ApiResponse(Status.Status_Ok,CustomConstants.CAT_POSTED,category);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }



        }

        return new ApiResponse(Status.Status_ERROR,CustomConstants.CATIMAGE_ERROR,null);
    }

    public Boolean saveCategoryImage(MultipartFile file, String name, String unique){
        try {

            String UPLOADED_FOLDER_NEW = CustomConstants.SERVER_PATH+"//"+"serverFiles//"+name+"//";

            File dir = new File(UPLOADED_FOLDER_NEW);
            dir.setExecutable(true);
            dir.setReadable(true);
            dir.setWritable(true);

            if(!dir.exists()){
                dir.mkdirs();
            }

            BufferedImage inputImage = ImageIO.read(file.getInputStream());

            BufferedImage resized = resize(inputImage, 30, 30);

            String format = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
            ImageIO.write(resized, format, new File(UPLOADED_FOLDER_NEW + unique+ file.getOriginalFilename()));

        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public ResponseEntity<InputStreamResource> getCategoryImage(String filename, String category) throws IOException{


        String filepath = CustomConstants.SERVER_PATH+"//"+"serverFiles//"+category+"//"+filename;

        File f = new File(filepath);
        Resource file = new UrlResource(f.toURI());
        return ResponseEntity
                .ok()
                .contentLength(file.contentLength())
                .contentType(
                        MediaType.parseMediaType("image/JPG"))
                .body(new InputStreamResource(file.getInputStream()));
    }

    public Category getById(Long id){
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isPresent()&&category.get().getActive()) {
           return category.get();
       }
       else {
           return new Category();
       }
    }

    public List <Category> getAll (){
        List <Category> categoryList =categoryRepository.getAll();
        return categoryList;
    }

    public  ApiResponse updateById (CategoryDTO categoryDTO, Long id) {

        Optional<Category> findCategory = categoryRepository.findById(id);
        Category category1 = findCategory.get();

        if (categoryDTO.getImage().getOriginalFilename().isEmpty()) {
            categoryDTO.setImage(null);
            return populateResponse(categoryDTO, category1);
        }
        if (category1.getImage()!= null && category1.getImage().equalsIgnoreCase(categoryDTO.getImage().getName())) {
            return populateResponse(categoryDTO, category1);
        }
        else {
            switch (profile){
                case CustomConstants.DEV:
                    String unique = String.valueOf(new Timestamp(System.currentTimeMillis()).getTime());

                    if(saveCategoryImage(categoryDTO.getImage(), categoryDTO.getName(), unique)){
                        category1.setName(categoryDTO.getName());
                        category1.setImage(categoryImageUrl+categoryDTO.getName()+"/"+unique+categoryDTO.getImage().getOriginalFilename());
                        categoryRepository.save(category1);
                        return new ApiResponse(200, CustomConstants.CAT_UPDATE,category1);
                    }
                break;

                case CustomConstants.PROD:
                    try {
                        Map map = cloudinaryService.upload(categoryDTO.getImage());
                        category1.setName(categoryDTO.getName());
                        category1.setImage(map.get("url").toString());
                        categoryRepository.save(category1);
                        return new ApiResponse(200, CustomConstants.CAT_UPDATE, category1);
                    }catch (IOException e) {
                        e.printStackTrace();
                    }
            }

            return new ApiResponse(401, CustomConstants.CATIMAGE_ERROR,null);
        }

    }

    public  ApiResponse  deleteCategory (Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        boolean  check=false;
        if (category.isPresent()) {
            check=true;
//            String folderPath = CustomConstants.SERVER_PATH+"//"+"serverFiles//"+category.get().getName();
//            File folder = new File(folderPath);
//            check =deleteDirectory(folder);
            List<Product> products = productsRepository.getAllByCategoryId(category.get().getId());
            for(Product pdt :products)
            {
                pdt.setActive(false);
                productsRepository.save(pdt);
            }

            category.get().setActive(false);
             categoryRepository.save(category.get());
//            categoryRepository.deleteById(id);
        }
            if(check) {
                return new ApiResponse(Status.Status_Ok, CustomConstants.CAT_DELETE, null, getAll());
            }else {
                return new ApiResponse(Status.Status_ERROR, CustomConstants.CATIMAGE_ERROR, null, getAll());
            }
    }

    public ApiResponse <Category> deleteAll (){
//    categoryRepository.deleteAll();

        List<Category> categories = categoryRepository.findAll();
        if(categories.size()>0){
            productsService.deleteAll();
        for(Category cat:categories)
        {
          cat.setActive(false);
        }}
     return new ApiResponse  (Status.Status_Ok, CustomConstants.CAT_DELETE, null);

    }


    private BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }

    private ApiResponse populateResponse(CategoryDTO categoryDTO, Category category1) {
        category1.setName(categoryDTO.getName());
        categoryRepository.save(category1);
        return new ApiResponse(200, CustomConstants.CAT_UPDATE, category1);
    }

    private boolean deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        return directoryToBeDeleted.delete();
    }


    public ApiResponse getSubCategoriesById(Long id) {
        if (id == 0)
        {
            return new ApiResponse(200,"Fetched All SubCategories", categoryRepository.getAllSubCategories());
        }
     return  new ApiResponse(200, "Fetched Sucessfully", categoryRepository.getSubCategoriesById(id))  ;
    }

    public List<Category> getByRestaurantId(Long id) {

       return categoryRepository.findByRetaurantId(id);
    }
}
