package com.example.terracebloom.Config;

import com.cloudinary.Cloudinary;
import com.cloudinary.Uploader;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {


    @Value("${CLOUDINARY_URL}")
    private String cloudinaryURL;

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(this.cloudinaryURL);
    }

    public Uploader uploader() {
        return cloudinary().uploader();
    }
}