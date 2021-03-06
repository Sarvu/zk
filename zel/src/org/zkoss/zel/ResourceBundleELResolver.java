/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.zkoss.zel;

import java.beans.FeatureDescriptor;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ResourceBundleELResolver extends ELResolver {

    public ResourceBundleELResolver() {
        super();
    }
    
    public Object getValue(ELContext context, Object base, Object property)
            throws NullPointerException, PropertyNotFoundException, ELException {
        if (context == null) {
            throw new NullPointerException();
        }
        
        if (base instanceof ResourceBundle) {
            if (property != null) {
                try {
                    Object result = ((ResourceBundle) base).getObject(property
                            .toString());
                    context.setPropertyResolved(true);
                    return result;
                } catch (MissingResourceException mre) {
                    return "???" + property.toString() + "???";
                }
            }
        }

        return null;
    }
    
    public Class<?> getType(ELContext context, Object base, Object property)
            throws NullPointerException, PropertyNotFoundException, ELException {
        if (context == null) {
            throw new NullPointerException();
        }
        
        if (base instanceof ResourceBundle) {
            context.setPropertyResolved(true);
        }
        
        return null;
    }
    
    public void setValue(ELContext context, Object base, Object property,
            Object value) throws NullPointerException,
            PropertyNotFoundException, PropertyNotWritableException,
            ELException {
        if (context == null) {
            throw new NullPointerException();
        }
        
        if (base instanceof ResourceBundle) {
            context.setPropertyResolved(true);
            throw new PropertyNotWritableException(message(context,
                    "resolverNotWriteable", new Object[] { base.getClass()
                            .getName() }));
        }
    }
    
    public boolean isReadOnly(ELContext context, Object base, Object property)
            throws NullPointerException, PropertyNotFoundException, ELException {
        if (context == null) {
            throw new NullPointerException();
        }
        
        if (base instanceof ResourceBundle) {
            context.setPropertyResolved(true);
        }
        
        return true;
    }

    //Dennis, 201206221, the override method cannot pass the build without warn because of the override generic type
    //and I don't know the reason they explained below, so I fix it , added the generic type.
    // Can't use Iterator<FeatureDescriptor> because API needs to match
    // specification
    //@SuppressWarnings({ "unchecked", "rawtypes" }) 
    public Iterator<java.beans.FeatureDescriptor> getFeatureDescriptors(
            ELContext context, Object base) {
        if (base instanceof ResourceBundle) {
            List<FeatureDescriptor> feats = new ArrayList<FeatureDescriptor>();
            Enumeration<String> e = ((ResourceBundle) base).getKeys();
            FeatureDescriptor feat;
            String key;
            while (e.hasMoreElements()) {
                key = e.nextElement();
                feat = new FeatureDescriptor();
                feat.setDisplayName(key);
                feat.setExpert(false);
                feat.setHidden(false);
                feat.setName(key);
                feat.setPreferred(true);
                feat.setValue(RESOLVABLE_AT_DESIGN_TIME, Boolean.TRUE);
                feat.setValue(TYPE, String.class);
                feats.add(feat);
            }
            return feats.iterator();
        }
        return null;
    }
    
    public Class<?> getCommonPropertyType(ELContext context, Object base) {
        if (base instanceof ResourceBundle) {
            return String.class;
        }
        return null;
    }

}
