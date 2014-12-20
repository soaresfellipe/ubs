package br.com.converter;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import org.primefaces.component.picklist.PickList;
import org.primefaces.model.DualListModel;

import br.com.modelo.Variavel;


@FacesConverter(value = "procVarConverter")
public class ProcVarConverter implements Converter {

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
				string = String.valueOf(((Variavel) object).getId());
			} catch (ClassCastException cce) {
				cce.printStackTrace();
				throw new ConverterException();
			}
		}
		return string;
	}

	@SuppressWarnings("unchecked")
	private Variavel getObjectFromUIPickListComponent(
			UIComponent component, String value) {
		final DualListModel<Variavel> dualList;
		try {
			dualList = (DualListModel<Variavel>) ((PickList) component)
					.getValue();
			Variavel Variavel = getObjectFromList(dualList.getSource(),
					Integer.valueOf(value));
			if (Variavel == null) {
				Variavel = getObjectFromList(dualList.getTarget(),
						Integer.valueOf(value));
			}

			return Variavel;
		} catch (ClassCastException cce) {
			cce.printStackTrace();
			throw new ConverterException();
		} catch (NumberFormatException nfe) {
			nfe.printStackTrace();
			throw new ConverterException();
		}
	}

	private Variavel getObjectFromList(final List<?> list,
			final Integer identifier) {
		for (final Object object : list) {
			final Variavel Variavel = (Variavel) object;
			if (Variavel.getId() == identifier) {
				return Variavel;
			}
		}
		return null;
	}
}