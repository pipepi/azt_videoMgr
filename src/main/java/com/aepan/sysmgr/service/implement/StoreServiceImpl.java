/**
 * 
 */
package com.aepan.sysmgr.service.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com._21cn.framework.util.PageList;
import com.aepan.sysmgr.dao.PackageStatDao;
import com.aepan.sysmgr.dao.ProductDao;
import com.aepan.sysmgr.dao.StoreDao;
import com.aepan.sysmgr.dao.VideoDao;
import com.aepan.sysmgr.model.ProductInfo;
import com.aepan.sysmgr.model.Store;
import com.aepan.sysmgr.model.StoreProduct;
import com.aepan.sysmgr.model.StoreProducts;
import com.aepan.sysmgr.model.StoreVideo;
import com.aepan.sysmgr.model.Video;
import com.aepan.sysmgr.model.hm.StoreSubInfo;
import com.aepan.sysmgr.model.lucene.ProductAttribute;
import com.aepan.sysmgr.model.packageinfo.PackageStat;
import com.aepan.sysmgr.service.ConfigService;
import com.aepan.sysmgr.service.StoreService;
import com.aepan.sysmgr.util.lucene.SearchHelper;
import com.aepan.sysmgr.web.controller.VideoController;

/**
 * @author rakika
 * 2015年8月10日下午5:15:54
 */
@Controller
public class StoreServiceImpl implements StoreService {
	
	private static final Logger logger = LoggerFactory.getLogger(VideoController.class);

	@Autowired
	StoreDao storeDao;
	@Autowired
	VideoDao videoDao;
	@Autowired
	ProductDao productDao;
	@Autowired
	private PackageStatDao packageStatDao;
	
	/* (non-Javadoc)
	 * @see com._aepan.sysmgr.service.StoreService#getList(java.util.Map, int, int)
	 */
	@Override
	public PageList<Store> getList(Map<String, Object> params, int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		PageList<Store> list = storeDao.getList(params, pageNo, pageSize); 
		//setStoreLogo(list);
		return list;
	}
	/**用播放器关联的第一个视频小图作为播放器的logo*/
	/*private void setStoreLogo(List<Store> list){
		if(list!=null&&!list.isEmpty()){
			for (Store store : list) {
				List<StoreVideo>  svList = videoDao.getStoreVideoList(store.getUserId(), store.getId());
				if(svList!=null&&svList.size()>0){
					int videoId = svList.get(0).getVideoId();
					if(videoId>0){
						Video v = videoDao.findVideoById(videoId); 
						if(v!=null){
							store.setLogoUrl(v.getImgMin());
						}
					}
				}
			}
		}
	}*/
	/* (non-Javadoc)
	 * @see com._aepan.sysmgr.service.StoreService#getOnlineListByUserId(int)
	 */
	@Override
	public List<Store> getOnlineListByUserId(int userId) {
		return storeDao.getOnlineListByUserId(userId);
	}

	/* (non-Javadoc)
	 * @see com._aepan.sysmgr.service.StoreService#getById(int)
	 */
	@Override
	public Store getById(int id) {
		// TODO Auto-generated method stub
		Store s = storeDao.getById(id);
		//设置已关联产品和视频数量，用于编辑视频页面判断是否显示内嵌码
		setLinkMsg(s);
		return s;
	}
	
	private void setLinkMsg(Store s){
		if(s!=null){
			//关联视频
			List<StoreVideo> svlist = videoDao.getStoreVideoList(s.getUserId(), s.getId());
			if(svlist!=null){
				s.setLinkedVideoNum(svlist.size());
				String lvids = "";
				if(svlist.size()>0){
					for (StoreVideo sv : svlist) {
						lvids+= sv.videoId+",";
					}
					lvids = lvids.substring(0,lvids.length()-1);
				}
				s.linkedVideoIds = lvids;
			}
			//关联产品
			List<StoreProduct> splist = productDao.getStoreProductList(s.getUserId(), s.getId());
			if(splist!=null){
				s.setLinkedProductNum(splist.size());
				String lpids = "";
				if(splist.size()>0){
					for (StoreProduct sp : splist) {
						lpids+= sp.productId+",";
					}
					lpids = lpids.substring(0,lpids.length()-1);
				}
				s.linkedProductIds = lpids;
			}
		}
	}
	/* (non-Javadoc)
	 * @see com._aepan.sysmgr.service.StoreService#updateStatus(int, int)
	 */
	@Override
	public boolean updateStatus(int id, int status) {
		// TODO Auto-generated method stub
		return storeDao.updateStatus(id, status);
	}

	/* (non-Javadoc)
	 * @see com._aepan.sysmgr.service.StoreService#getListByUserId(int)
	 */
	@Override
	public List<Store> getListByUserId(int userId) {
		// TODO Auto-generated method stub
		return storeDao.getListByUserId(userId);
	}
	@Override
	public List<StoreSubInfo> getStoreSubInfoByIds(List<Integer> ids){
		List<StoreSubInfo> list = storeDao.getStoreSubInfoByIds(ids);
		/*if(list!=null&&list.size()>0){
			for (StoreSubInfo ss : list) {
				List<StoreVideo> svlist = videoDao.getStoreVideoList(ss.getUserId(), ss.getId());
				//播放器logo设置为第一个视频的小图
				if(svlist!=null&&svlist.size()>0){
					int videoId = svlist.get(0).getVideoId();
					if(videoId>0){
						Video v = videoDao.findVideoById(videoId); 
						if(v!=null){
							ss.setImg(v.getImgMin());
						}
					}
				}
			}
		}*/
		return list;
	}
	@Override
	public List<Store> getListHasLinkedIds(int userId){
		List<Store> slist = storeDao.getListByUserId(userId);
		if(slist!=null&&slist.size()>0){
			for (Store s : slist) {
				//关联视频
				List<StoreVideo> svlist = videoDao.getStoreVideoList(userId, s.getId());
				if(svlist!=null){
					s.setLinkedVideoNum(svlist.size());
					String lvids = "";
					if(svlist.size()>0){
						for (StoreVideo sv : svlist) {
							lvids+= sv.videoId+",";
						}
						lvids = lvids.substring(0,lvids.length()-1);
					}
					s.linkedVideoIds = lvids;
				}
				//关联产品
				List<StoreProduct> splist = productDao.getStoreProductList(userId, s.getId());
				if(splist!=null){
					s.setLinkedProductNum(splist.size());
					String lpids = "";
					if(splist.size()>0){
						for (StoreProduct sp : splist) {
							lpids+= sp.productId+",";
						}
						lpids = lpids.substring(0,lpids.length()-1);
					}
					s.linkedProductIds = lpids;
				}
				s.escapeHtml();
			}
		}
		return slist;
	}

	@Override
	public StoreProducts getProductsByStoreId(int userId, int storeId) {
		// TODO Auto-generated method stub
		List<ProductInfo> plist = productDao.getByUserIdAndStoreIdList(userId, storeId);
		StoreProducts stProducts = new StoreProducts();
		stProducts.setStoreId(storeId);
		stProducts.setProductInfoList(plist);
		return stProducts;
	}

	/* (non-Javadoc)
	 * @see com._aepan.sysmgr.service.StoreService#save(com._aepan.sysmgr.model.Store)
	 */
	@Override
	public boolean save(Store store) {
		//更新拥有播放器数量
		PackageStat packageStat = packageStatDao.getByUserId(store.getUserId());
		packageStat.setPlayerNum(packageStat.getPlayerNum()+1);
		packageStatDao.updateUsedNum(packageStat);
		store.harmSensitiveWord();
		return storeDao.save(store);
	}
	@Override
	public void update(ConfigService configService,Store store){
		store.harmSensitiveWord();
		//store.addLucene();
		addLucene(configService,store);
		storeDao.update(store);
	}
	@Override
	public void delete(ConfigService configService,int storeId,int userId){
		storeDao.delete(storeId);
		//更新拥有播放器数量
		PackageStat packageStat = packageStatDao.getByUserId(userId);
		packageStat.setPlayerNum((packageStat.getPlayerNum()-1)>=0?(packageStat.getPlayerNum()-1):0);
		packageStatDao.updateUsedNum(packageStat);
		//删除播放器的和商品、视频的关联关系
		videoDao.deleteByUserIdANDStoreId(userId, storeId);
		productDao.deleteByUserIdANDStoreId(userId, storeId);
		//删除搜索索引
		SearchHelper.delete(configService, storeId+"");
		
	}
	@Override
	public int getLinkedProductNum(int storeId){
		List<Integer> list = productDao.getStoreProductIdList(storeId);
		return list==null?0:list.size();
	}
	@Override
	public int getLinkedVideoNum(int userId,int storeId){
		List<StoreVideo>  list =videoDao.getStoreVideoList(userId, storeId);
		return list==null?0:list.size();
	}
	@Override
	public void addLucene(ConfigService configService,int storeId){
		Store store = getById(storeId);
		if(store!=null){
			addLucene(configService,store);
		}
	}
	@Override
	public void addLucene(ConfigService configService,Store store){
		if(store==null) return;
		int userId = store.getUserId();
		Document doc = new Document();
		//part1:store    id name desc type     p_ids p_pricemax p_pricemin   v_hot v_img v_img_max
		//part2:product   p_name p_desc p_type p_attrname p_attrvalue
		//part3:video    v_id v_name v_desc 
		doc.add(new Field("id",store.getId()+"",Field.Store.YES,Field.Index.NOT_ANALYZED));
		doc.add(new Field("name",store.getName(),Field.Store.YES,Field.Index.ANALYZED) );
		doc.add(new Field("desc",store.getDescription(),Field.Store.YES,Field.Index.ANALYZED) );
		doc.add(new Field("type",store.getType(),Field.Store.YES,Field.Index.ANALYZED) );
		StringBuffer pIds = new StringBuffer();
		float priceMax = 0.0f;
		float priceMin = 0.0f;
		List<StoreProduct> spList = productDao.getStoreProductList(userId, store.getId());
		List<StoreVideo> svlist = videoDao.getStoreVideoList(userId, store.getId());
		if(spList==null||spList.isEmpty()||svlist==null||svlist.isEmpty()){
			SearchHelper.delete(configService, store.getId()+"");
			return;
		}
		if(spList!=null&&!spList.isEmpty()){
			for (StoreProduct sp : spList) {
				if(sp.productPrice>priceMax){
					priceMax = sp.productPrice;
				}
				if(sp.productPrice<priceMin){
					priceMin = sp.productPrice;
				}
				pIds.append(sp.getProductId()).append(",");
				if(sp.getProductName()!=null&&!sp.getProductName().isEmpty()){
					doc.add(new Field("p_name",sp.getProductName(),Field.Store.NO,Field.Index.ANALYZED) );
				}
				if(sp.getProductDesc()!=null&&!sp.getProductDesc().isEmpty()){
					logger.info("add product desc into lucene index lib:"+sp.getProductDesc());
					doc.add(new Field("p_desc",sp.getProductDesc(),Field.Store.NO,Field.Index.ANALYZED) );
				}
				if(sp.getProductType()!=null&&!sp.getProductType().isEmpty()){
					doc.add(new Field("p_type",sp.getProductType(),Field.Store.NO,Field.Index.ANALYZED) );
				}
				if(sp.productAttrs!=null&&!sp.productAttrs.isEmpty()){
					for (ProductAttribute pattr : sp.productAttrs) {
						doc.add(new Field("p_attrname",pattr.id.toString(),Field.Store.NO,Field.Index.NOT_ANALYZED) );
						for (Integer v : pattr.values) {
							doc.add(new Field("p_attrvalue",v.toString(),Field.Store.NO,Field.Index.NOT_ANALYZED) );
						}
					}
				}
			}
		}
		String pIdsStr = pIds.toString();
		if(pIdsStr.endsWith(",")){
			pIdsStr = pIdsStr.substring(0, pIdsStr.length()-1);
		}
		doc.add(new Field("p_ids",pIdsStr,Field.Store.YES,Field.Index.NO) );
		doc.add(new Field("p_pricemax",new Float(priceMax).intValue()+"",Field.Store.YES,Field.Index.NOT_ANALYZED) );
		doc.add(new Field("p_pricemin",new Float(priceMin).intValue()+"",Field.Store.YES,Field.Index.NOT_ANALYZED) );
		
		
		
		boolean storeImgFlag = false;
		int clickNum = 0;
		if(svlist!=null&&!svlist.isEmpty()){
			for (StoreVideo sv : svlist) {
				Video v = videoDao.findVideoById(sv.videoId);
				if(v!=null){
					if(!storeImgFlag){
						doc.add(new Field("v_img",v.getImgMin(),Field.Store.YES,Field.Index.NO) );
						doc.add(new Field("v_img_max",v.getImgMax(),Field.Store.YES,Field.Index.NO) );
						storeImgFlag = true;
					}
					doc.add(new Field("v_name",v.getName(),Field.Store.YES,Field.Index.ANALYZED) );
					doc.add(new Field("v_desc",v.getDesc(),Field.Store.YES,Field.Index.ANALYZED) );
					clickNum+=v.videoCnum;
					clickNum+=v.h5VideoCnum;
				}
			}
		}
		if(!storeImgFlag){
			doc.add(new Field("v_img","none",Field.Store.YES,Field.Index.NO) );
			doc.add(new Field("v_img_max","none",Field.Store.YES,Field.Index.NO) );
		}
		doc.add(new Field("v_hot",clickNum+"",Field.Store.YES,Field.Index.NOT_ANALYZED));
		SearchHelper.insert(configService,doc);
	}
	
	/**
	 * 通过storeId找到商家前5个播放器，不足5个时，找同类别点击量最大的播放器
	 * @param storeId
	 * @return
	 */
	@Override
	public List<StoreSubInfo> queryOthersByStoreId(int storeId){
		List<StoreSubInfo> rs = null;
		if(storeId>0){
			Store store = storeDao.getById(storeId);
			if(store!=null){
				List<StoreSubInfo> list = storeDao.getSellerOtherStores(store.getUserId(), store.getId());
				if(list!=null&&list.size()==5){
					rs = list;
				}else{
					String type = store.getType();
					String type1 = "";
					if(type==null||type.trim().isEmpty()){
						rs = list;
					}else if(type.indexOf(",")>-1){
						type1 = type.substring(0, type.indexOf(","));
					}else{
						type1 =  type;
					}
					int num = list==null?5:list.size();
					List<StoreSubInfo> list2 = storeDao.getCategoryOtherStores(store.getUserId(), type1, num);
					if(list==null){
						rs = list2;
					}else{
						if(list2!=null){
							list.addAll(list2);
						}
						rs = list;
					}
				}
			}
		}
		/*if(rs!=null&&rs.size()>0){
			for (StoreSubInfo ss : rs) {
				List<StoreVideo> svlist = videoDao.getStoreVideoList(ss.getUserId(), ss.getId());
				//播放器logo设置为第一个视频的小图
				if(svlist!=null&&svlist.size()>0){
					int videoId = svlist.get(0).getVideoId();
					if(videoId>0){
						Video v = videoDao.findVideoById(videoId); 
						if(v!=null){
							ss.setImg(v.getImgMin());
						}
					}
				}
			}
		}*/
		return rs;
	}
}
