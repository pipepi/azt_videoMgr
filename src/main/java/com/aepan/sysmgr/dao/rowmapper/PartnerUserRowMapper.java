/**
 * 
 */
package com.aepan.sysmgr.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.aepan.sysmgr.model.PartnerUser;

/**
 * himall user
 * @author rakika
 * 2015年8月1日下午5:54:40
 */
public class PartnerUserRowMapper implements RowMapper<PartnerUser>{

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public PartnerUser mapRow(ResultSet rs, int index) throws SQLException {
		PartnerUser himallUser = new PartnerUser();
		himallUser.setId(rs.getInt("id"));
		himallUser.setSellerName(rs.getString("seller_name"));
		himallUser.setHimallName(rs.getString("partner_account_name"));
		himallUser.setEmail(rs.getString("email"));
		himallUser.setPackageId(rs.getInt("package_id"));
		himallUser.setPackageName(rs.getString("package_name"));
		himallUser.setPlayerNum(rs.getInt("player_num"));
		himallUser.setVideoNum(rs.getInt("video_num"));
		himallUser.setProductNum(rs.getInt("product_num"));
		himallUser.setTotalPlayerNum(rs.getInt("total_player_num"));
		himallUser.setTotalVideoNum(rs.getInt("total_video_num"));
		himallUser.setTotalProductNum(rs.getInt("total_product_num"));
		himallUser.setPrice(rs.getFloat("price"));
		himallUser.setDuration(rs.getInt("duration"));
		himallUser.setEndTime(rs.getDate("end_time"));
		himallUser.setCreateTime(rs.getTimestamp("create_time"));
		return himallUser;
	}

}
