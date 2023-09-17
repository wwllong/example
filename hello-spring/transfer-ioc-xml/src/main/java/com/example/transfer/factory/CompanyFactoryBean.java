package com.example.transfer.factory;

import com.example.transfer.pojo.Company;
import org.springframework.beans.factory.FactoryBean;

/**
 * @author jack.wen
 * @since 2023/6/28 00:24
 */
public class CompanyFactoryBean implements FactoryBean<Company> {

    // 创建格式: 公司名称,地址,规模
    private String companyInfo;

    public void setCompanyInfo(String companyInfo) {
        this.companyInfo = companyInfo;
    }

    @Override
    public Company getObject() throws Exception {
        Company company = new Company();
        String[] strings = companyInfo.split(",");
        company.setName(strings[0]);
        company.setAddress(strings[1]);
        company.setScale(Integer.parseInt(strings[2]));
        return company;
    }

    @Override
    public Class<?> getObjectType() {
        return Company.class;
    }


    /**
     * 默认true，可以不覆盖
     */
    @Override
    public boolean isSingleton() {
        return FactoryBean.super.isSingleton();
    }

}
