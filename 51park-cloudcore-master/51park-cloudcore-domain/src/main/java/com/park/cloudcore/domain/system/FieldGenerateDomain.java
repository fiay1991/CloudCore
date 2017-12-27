package com.park.cloudcore.domain.system;

/**
 * 属性值生成
 * @author fangct on 20171225
 */
public class FieldGenerateDomain {
	//type, 1:临时车牌号生成
    private String id, type, remark,part_1,part_2,part_3;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPart_1() {
		return part_1;
	}

	public void setPart_1(String part_1) {
		this.part_1 = part_1;
	}

	public String getPart_2() {
		return part_2;
	}

	public void setPart_2(String part_2) {
		this.part_2 = part_2;
	}

	public String getPart_3() {
		return part_3;
	}

	public void setPart_3(String part_3) {
		this.part_3 = part_3;
	}

}
