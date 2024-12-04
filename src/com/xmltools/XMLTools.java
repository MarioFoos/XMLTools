package com.xmltools;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Herramients para el manejo de los XML
 * @author Mario Foos
 */
public class XMLTools
{
	private static final String DEF_DATE_FORMAT = "yyyyMMdd";

	/**
	 * Obtener el elemento del documento XML
	 * @param inStream Stream de entrada
	 * @return Elemento del documento
	 */
	public static Element getDocument(InputStream inStream)
	{
		try
		{
		      DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		      DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		      Document doc = docBuilder.parse(inStream);
		      doc.getDocumentElement().normalize();
		      return doc.getDocumentElement();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * Obtener el elemento del documento XML
	 * @param pathFile Ruta del archivo
	 * @return Elemento del documento
	 */
	public static Element getDocument(String pathFile)
	{
		try
		{
		      return getDocument(new FileInputStream(pathFile));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * Obtener una cadena desde un elemento xml
	 * @param element Elemento xml
	 * @return Cadena
	 */
	public static String toString(Element element)
	{
		try
		{
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
	        StringWriter writer = new StringWriter();
	        transformer.transform(new DOMSource(element), new StreamResult(writer));
	        return writer.getBuffer().toString();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/******************************************************************************************************/
	/*                                 GET DE DATOS TIPADOS XML                                           */
	/******************************************************************************************************/
	
	/**
	 * Obtener un array de strings de un nodo
	 * @param root Elemento padre
	 * @param names Nombres
	 * @return Array de strings
	 */
	public static ArrayList<String> getArrayString(Element root, String... names)
	{
		ArrayList<Element> elements = getElementChilds(root, names);
		ArrayList<String> strings = new ArrayList<>();
		for(int i = 0; i < elements.size(); ++i)
		{
			strings.add(elements.get(i).getTextContent());
		}
		return strings;
	}
	/**
	 * Obtener fecha desde un elemento con el formato por defecto
	 * @param root Elemento
	 * @param name Nombre del hijo
	 * @return Fecha
	 */
	public static Date getDate(Element root, String name)
	{
		return getDate(root, name, DEF_DATE_FORMAT);
	}
	/**
	 * Obtener fecha desde un elemento indicando el formato
	 * @param root Elemento
	 * @param format Formato de fecha
	 * @param names Nombre
	 * @return Fecha
	 */
	public static Date getDate(Element root, String format, String... names)
	{
		return getDate(root, null, format, names);
	}
	/**
	 * Obtener fecha desde un elemento indicando el formato
	 * @param root Elemento
	 * @param defValue Valor por defecto
	 * @param format Formato de fecha
	 * @param names Nombres
	 * @return Fecha
	 */
	public static Date getDate(Element root, Date defValue, String format, String... names)
	{
		String res = getString(root, names);
		if(res.isEmpty())
		{
			return defValue;
		}
		try
		{
			SimpleDateFormat formatter = new SimpleDateFormat(format);
			return formatter.parse(res);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return defValue;
	}
	/**
	 * Obtener un flotante desde un elemento
	 * @param root Elemento
	 * @param names Nombres
	 * @return Flotante
	 */
	public static double getDouble(Element root, String... names)
	{
		return getDouble(root, names);
	}
	/**
	 * Obtener un flotante desde un elemento
	 * @param root Elemento
	 * @param defValue Valor por defecto
	 * @param names Nombres
	 * @return Flotante
	 */
	public static double getDouble(Element root, double defValue, String... names)
	{
		String res = getString(root, names);
		if(res.isEmpty())
		{
			return defValue;
		}
		try
		{
			return Double.parseDouble(res);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return defValue;
	}
	/**
	 * Obtener un entero desde un elemento
	 * @param root Elemento
	 * @param names Nombres
	 * @return Entero
	 */
	public static int getInt(Element root, String... names)
	{
		return getInt(root, 0, names);
	}
	/**
	 * Obtener un entero desde un elemento
	 * @param root Elemento
	 * @param defValue Valor por defecto
	 * @param names Nombres
	 * @return Entero
	 */
	public static int getInt(Element root, int defValue, String... names)
	{
		String res = getString(root, names);
		if(res.isEmpty())
		{
			return defValue;
		}
		try
		{
			return Integer.parseInt(res);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return defValue;
	}
	/**
	 * Obtener un entero desde un elemento
	 * @param root Elemento
	 * @param names Nombres
	 * @return Entero
	 */
	public static long getLong(Element root, String... names)
	{
		return getLong(root, 0, names);
	}
	/**
	 * Obtener un entero desde un elemento
	 * @param root Elemento
	 * @param defValue Valor por defecto
	 * @param names Nombres
	 * @return Entero
	 */
	public static long getLong(Element root, long defValue, String... names)
	{
		String res = getString(root, names);
		if(res.isEmpty())
		{
			return defValue;
		}
		try
		{
			return Long.parseLong(res);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return defValue;
	}
	/**
	 * Obtener un booleano desde el elemento
	 * @param root Raiz
	 * @param strTrue Valor de true
	 * @param strFalse Valor de false
	 * @param defValue Por defecto
	 * @param names Nombres
	 * @return Booleano
	 */
	public static boolean getBoolean(Element root, String strTrue, String strFalse, boolean defValue, String... names)
	{
		String res = getString(root, names);
		if(strTrue.equalsIgnoreCase(res))
		{
			return true;
		}
		if(strFalse.equalsIgnoreCase(res))
		{
			return false;
		}
		return defValue;
	}
	/**
	 * Obtener una cadena desde un elemento
	 * @param root Nodo raíz
	 * @param names Nombres
	 * @return Cadena
	 */
	public static String getString(Element root, String... names)
	{
		String res = "";
		Element node = getElement(root, names);
		
		if(node != null && !hasChilds(node))
		{
			res = ComTools.trim(node.getTextContent().trim());
		}
		return res;
	}

	/******************************************************************************************************/
	/*                                        BÁSICOS XML                                                 */
	/******************************************************************************************************/
	
	/**
	 * Determina si tiene hijos
	 * @param root Elemento
	 * @return true si tiene hijoa
	 */
	public static boolean hasChilds(Element root)
	{
		NodeList nList = root.getChildNodes();
	    Node node;
		for(int i = 0; i < nList.getLength(); ++i)
		{
			if((node = nList.item(i)) != null)
			{
				if(node.getNodeType() == Node.ELEMENT_NODE)
				{
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * Retornar lista de hijos sin nulos
	 * @param root Nodo raiz
	 * @param names Nombres
	 * @return Hijos
	 */
	public static ArrayList<Element> getElementChilds(Element root, String... names)
	{
		if(root == null)
		{
			return new ArrayList<>();
		}
		if(names.length == 0)
		{
			NodeList nList = root.getChildNodes();
		    Node node;
		    ArrayList<Element> elements = new ArrayList<>();
			for(int i = 0; i < nList.getLength(); ++i)
			{
				if((node = nList.item(i)) != null)
				{
					if(node.getNodeType() == Node.ELEMENT_NODE)
					{
						elements.add((Element) node);
					}
				}
			}
			return elements;
		}
		else
		{
			return getElementChilds(getElement(root, names));
		}
	}	
	/**
	 * Obtener lista de elementos en la ruta de nombres dada
	 * @param root Raiz
	 * @param names Nombres
	 * @return Elementos
	 */
	public static ArrayList<Element> getElements(Element root, String... names)
	{
		if(root == null || names.length == 0)
		{
			return new ArrayList<>();
		}
		Element node = root;
		int len = names.length;
		for(int i = 0; i < len - 1; ++i)
		{
			node = getElementByName(node, names[i]);
			if(node == null)
			{
				return null;
			}
		}
		return getElementsByName(node, names[len - 1]);
	}
	/**
	 * Obtener elemento en la ruta de nombres dada
	 * @param root Raiz
	 * @param names Nombres
	 * @return Elemento
	 */
	public static Element getElement(Element root, String... names)
	{
		if(root == null || names.length == 0)
		{
			return root;
		}
		Element node = root;
		for(int i = 0; i < names.length; ++i)
		{
			node = getElementByName(node, names[i]);
			if(node == null)
			{
				break;
			}
		}
		return node;
	}
	/**
	 * Obtener lista de elementos con el nombre del nodo
	 * @param root Nodo raiz
	 * @param name Nombre
	 * @return Lista de nodos con ese nombre
	 */
	public static ArrayList<Element> getElementsByName(Element root, String name)
	{
		if(root == null || name == null || name.isEmpty())
		{
			return new ArrayList<>();
		}
		NodeList nList = root.getChildNodes();
		Node node;
		ArrayList<Element> elements = new ArrayList<>();
		for(int i = 0; i < nList.getLength(); ++i)
		{
			if((node = nList.item(i)) != null)
			{
				if((node.getNodeType() == Node.ELEMENT_NODE) && name.equalsIgnoreCase(node.getNodeName()))
				{
					elements.add((Element) node);
				}
			}
		}
		return elements;
	}
	/**
	 * Obtener el hijo de un elemento
	 * @param root Elemento
	 * @param name Nombre del hijo
	 * @return Elemento hijo
	 */
	public static Element getElementByName(Element root, String name)
	{
		if(root == null || name == null || name.isEmpty())
		{
			return null;
		}
		NodeList nList = root.getChildNodes();
	    Node node;
		for(int i = 0; i < nList.getLength(); ++i)
		{
			if((node = nList.item(i)) != null)
			{
				if((node.getNodeType() == Node.ELEMENT_NODE) && name.equalsIgnoreCase(node.getNodeName()))
				{
					return (Element) node;
				}
			}
		}
		return null;
	}
}
