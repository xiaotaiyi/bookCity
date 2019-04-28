package com.xiaodongbaobao.novel.bean;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.COSObject;
import com.qcloud.cos.model.GetObjectRequest;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.region.Region;

@Component
public class CosProcess {

	private String bucketName;
	private COSClient cosClient;

	public CosProcess ( @Value("${app.qq.cos.secretId}") String secretId,
						@Value("${app.qq.cos.secretKey}") String secretKey,
						@Value("${app.qq.cos.bucketName}") String bucketName){
		this.bucketName = bucketName;
		// 1 初始化用户身份信息（secretId, secretKey）。
		COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
		// 2 设置bucket的区域, COS地域的简称请参照
		// https://cloud.tencent.com/document/product/436/6224
		// clientConfig中包含了设置 region, https(默认 http), 超时, 代理等 set 方法,
		// 使用可参见源码或者接口文档 FAQ 中说明。
		ClientConfig clientConfig = new ClientConfig(new Region("ap-beijing"));
		// 3 生成 cos 客户端。
		clientConfig.setHttpProtocol(HttpProtocol.https);
		cosClient = new COSClient(cred, clientConfig);
	}

	public COSClient getCosClient() {
		return cosClient;
	}

	// 创建小说名文件夹
	public void createFolder(String key) {
		// 文件夹名必须以'/'结尾
		key = key.endsWith("/") ? key : key + '/';
		// 目录对象即是一个/结尾的空文件，上传一个长度为 0 的 byte 流
		InputStream input = new ByteArrayInputStream(new byte[0]);
		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentLength(0);
		PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, input, objectMetadata);
		cosClient.putObject(putObjectRequest);
	}

	// 检查文件夹是否存在
	public Boolean checkFolderExist(String key) {
		return cosClient.doesObjectExist(bucketName, key + "/");
	}

	// 创建文件
	public void createFile(String key, InputStream stream) throws IOException {
		// 设置ObjectMetadata
		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentLength(stream.available());
		objectMetadata.setContentType("text/plain");
		PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, stream, objectMetadata);
		cosClient.putObject(putObjectRequest);
	}

	// 检查文件夹是否存在
	public Boolean checkFileExist(String key) {
		return cosClient.doesObjectExist(bucketName, key);
	}
	
	public String getContent(String key){
		GetObjectRequest rq = new GetObjectRequest(bucketName, key);
		COSObject cosObject = cosClient.getObject(rq);
		return getStringFromInputStream(cosObject.getObjectContent());
	}
	public static String getStringFromInputStream(InputStream inputStream) {
        String resultData = null;      //需要返回的结果
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] data = new byte[2048];
        int len = 0;
        try {
            while((len = inputStream.read(data)) != -1) {
                byteArrayOutputStream.write(data, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        resultData = new String(byteArrayOutputStream.toByteArray());    
        return resultData;
    }
	// 创建图片
	public void createImage(String key, InputStream stream) throws IOException {
			// 设置ObjectMetadata
			ObjectMetadata objectMetadata = new ObjectMetadata();
			objectMetadata.setContentLength(stream.available());
			objectMetadata.setContentType("image/jpeg");
			PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, stream, objectMetadata);
			cosClient.putObject(putObjectRequest);
		}
		// 创建语音
	public void createMP3(String key, InputStream stream) throws IOException {
			// 设置ObjectMetadata
			ObjectMetadata objectMetadata = new ObjectMetadata();
			objectMetadata.setContentLength(stream.available());
			objectMetadata.setContentType("audio/mpeg");
			PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, stream, objectMetadata);
			cosClient.putObject(putObjectRequest);
	}


}
