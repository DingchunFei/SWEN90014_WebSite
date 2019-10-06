package com.fei.domain;

import java.util.List;

public class TrialResultShape {

    private String id;
    private String trial_result_id;
    private String shape_id;
    private Integer type;
    private Integer hit_count;
    private Integer grid_row;
    private Integer grid_column;

    private Boolean correctness;
    private String touchOrderListIntoString;

    private List<TouchOrder> touchOrderList;
    private Shape shape;
    private TrialResult trialResult;

    @Override
    public String toString() {
        return "TrialResultShape{" +
                "id='" + id + '\'' +
                ", trial_result_id='" + trial_result_id + '\'' +
                ", shape_id='" + shape_id + '\'' +
                ", type=" + type +
                ", hit_count=" + hit_count +
                ", grid_row=" + grid_row +
                ", grid_column=" + grid_column +
                ", correctness=" + correctness +
                ", touchOrderListIntoString='" + touchOrderListIntoString + '\'' +
                ", touchOrderList=" + touchOrderList +
                ", shape=" + shape +
                '}';
    }

    public void readyToShow(){
        
        if(type == 0){
            if(hit_count%2==0) { //没有点
                correctness = false;
            }else{
                correctness = true;
            }

            StringBuilder sb = new StringBuilder (shape.getS_name());
            sb.insert(sb.length()-4, "_G");

            Shape shape_new = new Shape();
            shape_new.setId(shape.getId());
            shape_new.setS_name(sb.toString());
            setShape(shape_new);

        }else if(type == 1){
            if(hit_count%2==0) { //没有点
                correctness = true;
            }else{
                correctness = false;
            }
        }else if(type ==2){
            if(hit_count%2==0) { //没有点
                correctness = true;
            }else{
                correctness = false;
            }
            StringBuilder sb = new StringBuilder (shape.getS_name());
            sb.insert(sb.length()-4, "_B");

            Shape shape_new = new Shape();
            shape_new.setId(shape.getId());
            shape_new.setS_name(sb.toString());
            setShape(shape_new);
        }


        StringBuilder sb2 = new StringBuilder();
        for (TouchOrder touchOrder: touchOrderList) {
            sb2.append(touchOrder.getTouch_time()+"s ");
        }
        setTouchOrderListIntoString(sb2.toString());
    }

    public String getTouchOrderListIntoString() {
        return touchOrderListIntoString;
    }

    public void setTouchOrderListIntoString(String touchOrderListIntoString) {
        this.touchOrderListIntoString = touchOrderListIntoString;
    }

    public Boolean getCorrectness() {
        return correctness;
    }

    public void setCorrectness(Boolean correctness) {
        this.correctness = correctness;
    }

    public String getId() {
        return id;
    }

    public Integer getGrid_row() {
        return grid_row;
    }

    public void setGrid_row(Integer grid_row) {
        this.grid_row = grid_row;
    }

    public Integer getGrid_column() {
        return grid_column;
    }

    public void setGrid_column(Integer grid_column) {
        this.grid_column = grid_column;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTrial_result_id() {
        return trial_result_id;
    }

    public void setTrial_result_id(String trial_result_id) {
        this.trial_result_id = trial_result_id;
    }

    public String getShape_id() {
        return shape_id;
    }

    public void setShape_id(String shape_id) {
        this.shape_id = shape_id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getHit_count() {
        return hit_count;
    }

    public void setHit_count(Integer hit_count) {
        this.hit_count = hit_count;

    }

    public List<TouchOrder> getTouchOrderList() {
        return touchOrderList;
    }

    public void setTouchOrderList(List<TouchOrder> touchOrderList) {
        this.touchOrderList = touchOrderList;
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public TrialResult getTrialResult() {
        return trialResult;
    }

    public void setTrialResult(TrialResult trialResult) {
        this.trialResult = trialResult;
    }
}
