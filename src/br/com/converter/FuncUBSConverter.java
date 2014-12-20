package br.com.converter;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import org.primefaces.component.picklist.PickList;
import org.primefaces.model.DualListModel;

import br.com.modelo.UBS;


@FacesConverter(value = "funcUBSConverter")
public class FuncUBSConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		return getObjectFromUIPickListComponent(component, value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object object) {
		String string;
		if (object == null) {
			string = "";
		} else {
			try {
				string = String.valueOf(((UBS) object).getId());
			} catch (ClassCastException cce) {
				cce.printStackTrace();
				throw new ConverterException();
			}
		}
		return string;
	}

	@SuppressWarnings("unchecked")
	private UBS getObjectFromUIPickListComponent(
			UIComponent component, String value) {
		final DualListModel<UBS> dualList;
		try {
			dualList = (DualListModel<UBS>) ((PickList) component)
					.getValue();
			UBS ubs = getObjectFromList(dualList.getSource(),
					Integer.valueOf(value));
			if (ubs == null) {
				ubs = getObjectFromList(dualList.getTarget(),
						Integer.valueOf(value));
			}

			return ubs;
		} catch (ClassCastException cce) {
			cce.printStackTrace();
			throw new ConverterException();
		} catch (NumberFormatException nfe) {
			nfe.printStackTrace();
			throw new ConverterException();
		}
	}

	private UBS getObjectFromList(final List<?> list,
			final Integer identifier) {
		for (final Object object : list) {
			final UBS ubs = (UBS) object;
			if (ubs.getId() == identifier) {
				return ubs;
			}
		}
		return null;
	}
}
