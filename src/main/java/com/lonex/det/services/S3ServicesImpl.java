package com.lonex.det.services;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
 
@Service
public class S3ServicesImpl {
	
  @Autowired
  private AmazonS3 s3client;
 
  @Value("${jsa.s3.bucket}")
  private String bucketName;
 
  
  public InputStream downloadFile(String fileName) {
    
    try {
            System.out.println("Downloading an object from bucket " + bucketName);
            S3Object s3object = s3client.getObject(new GetObjectRequest(bucketName, fileName));
            System.out.println("Content-Type: "  + s3object.getObjectMetadata().getContentType());
            
          
            System.out.println("===================== Import File - Done! =====================");
            return s3object.getObjectContent();
            
        } catch (AmazonServiceException ase) {
          System.out.println("Caught an AmazonServiceException from GET requests, rejected reasons:");
      System.out.println("Error Message:    " + ase.getMessage());
      System.out.println("HTTP Status Code: " + ase.getStatusCode());
      System.out.println("AWS Error Code:   " + ase.getErrorCode());
      System.out.println("Error Type:       " + ase.getErrorType());
      System.out.println("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
          System.out.println("Caught an AmazonClientException: ");
            System.out.println("Error Message: " + ace.getMessage());
        }
    return null;
  }
 
  
  public boolean uploadDataFile(String fileName, InputStream inputStream) {
    
    try {
      
          s3client.putObject(new PutObjectRequest(bucketName, fileName, inputStream , new ObjectMetadata()));
          System.out.println("===================== Upload File - Done! =====================");
          return true;
          
    } catch (AmazonServiceException ase) {
      System.out.println("Caught an AmazonServiceException from PUT requests, rejected reasons:");
      System.out.println("Error Message:    " + ase.getMessage());
      System.out.println("HTTP Status Code: " + ase.getStatusCode());
      System.out.println("AWS Error Code:   " + ase.getErrorCode());
      System.out.println("Error Type:       " + ase.getErrorType());
      System.out.println("Request ID:       " + ase.getRequestId());
      return false;
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException: ");
            System.out.println("Error Message: " + ace.getMessage());
            return false;
        }
  }


public boolean uploadFile(String originalFilename, InputStream inputStream) {
	  try {
          s3client.putObject(new PutObjectRequest(bucketName , "vendors/product/upload/" + originalFilename , inputStream, new ObjectMetadata()).withCannedAcl(CannedAccessControlList.PublicRead));
          System.out.println("===================== Upload File - Done! =====================");
          return true;
          
    } catch (AmazonServiceException ase) {
      System.out.println("Caught an AmazonServiceException from PUT requests, rejected reasons:");
      System.out.println("Error Message:    " + ase.getMessage());
      System.out.println("HTTP Status Code: " + ase.getStatusCode());
      System.out.println("AWS Error Code:   " + ase.getErrorCode());
      System.out.println("Error Type:       " + ase.getErrorType());
      System.out.println("Request ID:       " + ase.getRequestId());
      return false;
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException: ");
            System.out.println("Error Message: " + ace.getMessage());
            return false;
        }
	
}
 
}