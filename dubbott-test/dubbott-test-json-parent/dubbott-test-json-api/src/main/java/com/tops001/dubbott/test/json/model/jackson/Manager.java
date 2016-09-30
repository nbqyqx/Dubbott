package com.tops001.dubbott.test.json.model.jackson;

/**
 * If a change is made to this class, it must also be updated in lib/appclasses.jar\com\ibm\ws\jaxrs\fat\jackson\
 */
public class Manager {

    private String managerName;
    private long managerId;

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public long getManagerId() {
        return managerId;
    }

    public void setManagerId(long managerId) {
        this.managerId = managerId;
    }

}
