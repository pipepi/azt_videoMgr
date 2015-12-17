/**
 * 
 */
package com.aepan.sysmgr.model.search;

import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;


/**
 * 播放器搜索关键词
 * @author lanker
 * 2015年10月29日上午11:22:50
 */
public class StoreKeyWords {
	public Integer id;
	public String name;
	public String desc;
	public String types;
	public List<VideoKeyWords> videoKeys; 
	public List<ProductKeyWords> productKeys; 
	public static void add(StoreKeyWords skw){
		if(skw==null||skw.videoKeys==null||skw.productKeys==null||
				skw.videoKeys.isEmpty()||skw.productKeys.isEmpty()){
			return;//未同时关联视频和产品的时候，不建立词条
		}
		
	}
	public static void updateByStoreKW(StoreKeyWords skw){
		StoreKeyWords oldSKW  = null;//通过播放器id从词库中获取旧的关键词信息
	}
	private static Document getDoc(StoreKeyWords skw){
		Document doc = new Document();
		doc.add(new Field("id",skw.id.toString(),Field.Store.YES,Field.Index.NO));
		doc.add(new Field("name",skw.name,Field.Store.YES,Field.Index.NO));
		doc.add(new Field("desc",skw.desc,Field.Store.YES,Field.Index.NO));
		doc.add(new Field("types",skw.types,Field.Store.YES,Field.Index.NO));
		for (VideoKeyWords v : skw.videoKeys) {
			
		}
		return doc;
	}
}
