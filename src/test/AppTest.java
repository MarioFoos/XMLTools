package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Level;
import javax.xml.bind.JAXBContext;
import org.w3c.dom.Element;

/**
 * @hidden
 */
@SuppressWarnings({"unchecked", "unused"})
public class AppTest
{
	private static Element testElement;
	private static String TEST_FILE = "test.xml";

	
	public static void main(String[] args)
	{
		try
		{
			InputStream inputStream = new FileInputStream(new File(TEST_FILE));
			testElement = com.xmltools.XMLTools.getDocument(inputStream);
			inputStream.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		System.out.println("name: "+ com.xmltools.XMLTools.getString(testElement, "misc", "name"));
		System.out.println("age: "+ com.xmltools.XMLTools.getInt(testElement, "misc", "age"));
		System.out.println("weigth: "+ com.xmltools.XMLTools.getString(testElement, "misc", "weigth"));
		System.out.println("list: "+ com.xmltools.XMLTools.getArrayString(testElement, "names"));
		
		
	}
}
