package com.termpaper.medlab.model;

import java.io.InputStream;

public class PhotoToUpload{
    
	private InputStream inputStream;

	private byte[] blob;

	public PhotoToUpload() {
	}
	
	public PhotoToUpload(byte[] blob){
	    this.blob = blob;
	}
	
	public PhotoToUpload(InputStream stream){
	    this.inputStream = stream;
	}	

	public InputStream getStream() {
		return inputStream;
	}

	public void setStream(InputStream stream) {
		this.inputStream = stream;
	}

	public byte[] getBlob() {
		return blob;
	}

	public void setBlob(byte[] blobAsBytes) {
		this.blob = blobAsBytes;
	}
}