package br.com.controle;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.persistence.Query;

import org.apache.log4j.Logger;

import br.com.dao.GenericoDAOJPA;
import br.com.modelo.Pessoa;

public class LoginControle extends GenericoDAOJPA<Pessoa> implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2869424403478128399L;
	
	private static final Logger logger = Logger.getLogger(LoginControle.class.getName());

	public Pessoa validaLogin(String cpf, String senha, Date dataNasc) {
		logger.debug("Método que realiza a validação do Login na aplicação");
		Query q = em.createNamedQuery("Pessoa.validaLogin");
		q.setParameter("cpf", cpf);
		q.setParameter("senha", convertStringToMd5(senha));
		q.setParameter("dataNasc", dataNasc);
		logger.debug(q.toString());
		return (Pessoa) q.getSingleResult();
	}

	private String convertStringToMd5(String valor) {
		MessageDigest mDigest;
		try {
			// Instanciamos o nosso HASH MD5.
			mDigest = MessageDigest.getInstance("MD5");

			// Convert a String valor para um array de bytes em MD5
			byte[] valorMD5 = mDigest.digest(valor.getBytes("UTF-8"));

			// Convertemos os bytes para hexadecimal, assim podemos salvar
			// no banco para posterior comparação se senhas
			StringBuffer sb = new StringBuffer();
			for (byte b : valorMD5) {
				sb.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1,
						3));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}