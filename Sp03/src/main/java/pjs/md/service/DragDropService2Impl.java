package pjs.md.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import lombok.extern.log4j.Log4j;
import pjs.md.fileset.Path;

@Log4j
@Service
public class DragDropService2Impl implements DragDropService2 {
	private MultipartHttpServletRequest multipartRequest;
	
	
	@Override
	public MultipartHttpServletRequest getMultipartRequest() {
		return multipartRequest;
	}
	
	@Override
	public void setMultipartRequest(MultipartHttpServletRequest multipartRequest) {
		this.multipartRequest = multipartRequest;
	}
	
	private Map<String, List<Object>> map;
	@Override
	public Map<String, List<Object>> getUpdateFileName() {
		upload();
		return map;
	}
	private void upload() {
		map = new Hashtable<String, List<Object>>();
		
		Iterator<String> itr = multipartRequest.getFileNames();
		List<Object> ofnames = new ArrayList<Object>();
		List<Object> savefnames = new ArrayList<Object>();
		List<Object> fsizes = new ArrayList<Object>();
		
		while(itr.hasNext()) {
			StringBuilder sb = new StringBuilder();
			MultipartFile mpf = multipartRequest.getFile(itr.next());
			String ofname = mpf.getOriginalFilename();
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
			String ctm = sdf.format(System.currentTimeMillis());
			
			sb.append(ctm)
			.append(UUID.randomUUID().toString())
			.append(ofname.substring(ofname.lastIndexOf(".")));
			String savefname = sb.toString();
			
			long fsize = mpf.getSize();
			
			String fileFullPath = Path.FILE_STORE + savefname;
			try {
				mpf.transferTo(new File(fileFullPath));
				ofnames.add(ofname);
				savefnames.add(savefname);
				fsizes.add(fsize);
			}
			catch(IOException ie) {}
		}
		map.put("ofnames", ofnames);
		map.put("savefnames", savefnames);
		map.put("fsizes", fsizes);
	}
}
