package com.example.TucShopBackend.Services;

import com.example.TucShopBackend.Commons.ApiResponse;
import com.example.TucShopBackend.Commons.CustomConstants;
import com.example.TucShopBackend.Commons.Status;
import com.example.TucShopBackend.DTO.RestaurantDTO;
import com.example.TucShopBackend.Models.Menu;
import com.example.TucShopBackend.Models.Restaurant;
import com.example.TucShopBackend.Repositories.MenuRepository;
import com.example.TucShopBackend.Repositories.RestaurantRepository;
import com.example.TucShopBackend.Repositories.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;



@Service
public class RestaurantService {

    @Value("${restaurant.image.url}")
    String restaurantImageUrl;

    @Autowired
    RestaurantRepository restaurantRepository;
    @Autowired
    MenuRepository menuRepository;
    @Autowired
    UserDao userDao;

    public ApiResponse  addRestaurant(RestaurantDTO restaurantDTO) {

       List<Restaurant> restaurantList = restaurantRepository.findAll();

        if(restaurantList.isEmpty()){
            String folderPath = CustomConstants.SERVER_PATH+"//"+"serverFilesRestaurant//"+"//";
            File folder = new File(folderPath);
             deleteDirectory(folder);
        }

       Restaurant restaurantFind = restaurantRepository.getByRestaurantName(restaurantDTO.getRestaurantName());
        if(restaurantFind != null){
            return new ApiResponse(Status.Status_ERROR,"Restaurant Already Register by this name",null);
        }
        else{

            String unique = String.valueOf(new Timestamp(System.currentTimeMillis()).getTime());
            if(saveRestaurantImage(restaurantDTO.getRestaurantImage(),restaurantDTO.getRestaurantName(),unique)){

                Restaurant restaurant = new Restaurant();
                restaurant.setRestaurantName(restaurantDTO.getRestaurantName());
                restaurant.setRestaurantAddress(restaurantDTO.getRestaurantAddress());
                restaurant.setRestaurantContactNumber(restaurantDTO.getRestaurantContactNumber());
                restaurant.setRestaurantEmail(restaurantDTO.getRestaurantEmail());
                restaurant.setRestaurantType(restaurantDTO.getRestaurantType());
                restaurant.setUser(userDao.findById(restaurantDTO.getUserId()).get());
                restaurant.setRestaurantImage(restaurantImageUrl + restaurantDTO.getRestaurantName() + "/" + unique + restaurantDTO.getRestaurantImage().getOriginalFilename());
                restaurant.setActive(true);
                restaurantRepository.save(restaurant);
                Menu menu = new Menu();
                menu.setRestaurant(restaurant);
                menu.setActive(true);
                menuRepository.save(menu);
                return new ApiResponse(Status.Status_Ok,"Success",restaurant);
            }

            }
        return new ApiResponse(Status.Status_ERROR,CustomConstants.RES_IMAGE_ERROR,null);
    }


    public Boolean saveRestaurantImage(MultipartFile file, String name, String unique){
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
            String format = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
            ImageIO.write(inputImage, format, new File(UPLOADED_FOLDER_NEW + unique+ file.getOriginalFilename()));

        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
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

    public ResponseEntity<InputStreamResource> getRestaurantImage(String filename, String restaurantName) throws IOException{

        String filepath = CustomConstants.SERVER_PATH+"//"+"serverFiles//"+restaurantName+"//"+filename;

        File f = new File(filepath);
        Resource file = new UrlResource(f.toURI());
        return ResponseEntity
                .ok()
                .contentLength(file.contentLength())
                .contentType(
                        MediaType.parseMediaType("image/JPG"))
                .body(new InputStreamResource(file.getInputStream()));

    }

    public ApiResponse getRestaurantList(){

        List<Restaurant> restaurantList = restaurantRepository.findAll();
        return new ApiResponse(Status.Status_Ok,"Success",restaurantList);
    }

    public ApiResponse getRestaurantById(Long id){




        Optional<Restaurant> restaurantFindByID = restaurantRepository.findById(id);
        if(restaurantFindByID!=null){
            Restaurant restaurant = restaurantFindByID.get();

            return new ApiResponse(Status.Status_Ok,"Successfully get",restaurant);
        }
        else {
            return new ApiResponse(Status.Status_Ok,"Not Found",null);
        }

    }

    public ApiResponse updateRestaurant(Long id,RestaurantDTO restaurantDTO){

        Optional<Restaurant> restaurant = restaurantRepository.findById(id);

        if(restaurant!=null){

            Restaurant restaurantToUpdate = restaurant.get();

            restaurantToUpdate.setRestaurantName(restaurantDTO.getRestaurantName());
            restaurantToUpdate.setRestaurantType(restaurantDTO.getRestaurantType());
            restaurantToUpdate.setRestaurantContactNumber(restaurantDTO.getRestaurantContactNumber());
            restaurantToUpdate.setRestaurantAddress(restaurantDTO.getRestaurantAddress());
            restaurantToUpdate.setRestaurantEmail(restaurantDTO.getRestaurantEmail());
            restaurantToUpdate.setActive(true);
            restaurantRepository.save(restaurantToUpdate);

            return new ApiResponse(Status.Status_Ok,"Successfully Updated",restaurantToUpdate);
        }
        else {
            return  new ApiResponse(Status.Status_ERROR,"Some thing went Wrong",null);
        }

    }

    public ApiResponse deleteRestaurantById(Long id){

        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(id);
        if(restaurantOptional!=null){
            Restaurant restaurant = restaurantOptional.get();
            restaurant.setActive(false);

            restaurantRepository.save(restaurant);
            return new ApiResponse(Status.Status_Ok,"Successfully Deleted",restaurant);
        }
        return new ApiResponse(Status.Status_ERROR,"Some thing Went Wrong",null);
    }


}