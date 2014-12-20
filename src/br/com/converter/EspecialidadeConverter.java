package br.com.converter;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import org.primefaces.component.picklist.PickList;
import org.primefaces.model.DualListModel;

import br.com.modelo.Especialidade;

@FacesConverter(value = "especialidadeConverter")
public class EspecialidadeConverter implements Converter {

	  
	    @Override  
	    public Object getAsObject(FacesContext context, UIComponent component, String value) {  
	        return getObjectFromUIPickListComponent(component, value);  
	    }  
	  
	    @Override  
	    public String getAsString(FacesContext context, UIComponent component, Object object) {  
	        String string;  
	        if (object == null) {  
	            string = "";  
	        } else {  
	            try {  
	                string = String.valueOf(((Especialidade) object).getId());  
	            } catch (ClassCastException cce) {  
	                cce.printStackTrace();  
	                throw new ConverterException();  
	            }  
	        }  
	        return string;  
	    }  
	  
	    @SuppressWarnings("unchecked")  
	    private Especialidade getObjectFromUIPickListComponent(UIComponent component, String value) {  
	        final DualListModel<Especialidade> dualList;  
	        try {  
	            dualList = (DualListModel<Especialidade>) ((PickList) component).getValue();  
	            Especialidade esp = getObjectFromList(dualList.getSource(), Integer.valueOf(value));  
	            if (esp == null) {  
	                esp = getObjectFromList(dualList.getTarget(), Integer.valueOf(value));  
	            }  
	  
	            return esp;  
	        } catch (ClassCastException cce) {  
	            cce.printStackTrace();  
	            throw new ConverterException();  
	        } catch (NumberFormatException nfe) {  
	            nfe.printStackTrace();  
	            throw new ConverterException();  
	        }  
	    }  
	  
	    private Especialidade getObjectFromList(final List<?> list, final Integer identifier) {  
	        for (final Object object : list) {  
	            final Especialidade esp = (Especialidade) object;  
	            if (esp.getId()==identifier) {  
	                return esp;  
	            }  
	        }  
	        return null;  
	    }  
	}  
