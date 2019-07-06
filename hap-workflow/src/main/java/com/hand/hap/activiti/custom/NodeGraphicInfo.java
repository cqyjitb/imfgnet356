package com.hand.hap.activiti.custom;

import org.activiti.bpmn.model.GraphicInfo;

import java.awt.*;

/**
 * @author njq.niu@hand-china.com
 */
public class NodeGraphicInfo extends GraphicInfo {

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    private Color color;

}
