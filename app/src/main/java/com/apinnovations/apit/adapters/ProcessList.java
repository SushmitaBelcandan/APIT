package com.apinnovations.apit.adapters;

public class ProcessList {

    private String strProcess;

    public ProcessList(String processName){

        this.strProcess = processName;
    }

    public String getTitle(){

        return strProcess;
    }

    public void setTitle(String processObj){

        this.strProcess = processObj;
    }

}
