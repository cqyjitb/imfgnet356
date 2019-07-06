package com.hand.hap.core.web.view.xpath;

import com.hand.hap.core.web.view.XMap;

public class LastPredicate implements Predicate {

    public LastPredicate(){
        
    }
    
    @Override
    public boolean validate(XMap node) {
        XMap parent = node.getParent();
        if (parent != null) {
            return parent.getChildren().get(parent.getChildren().size()-1) == node;
        }
        return false;
    }

}
