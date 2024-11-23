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

import com.xmltools.XMLTools;

/**
 * @hidden
 */
@SuppressWarnings({"unchecked", "unused"})
public class AppTest
{
	private static Element testElement;
	private static Element test2Element;
	private static String TEST_FILE = "test.xml";
	private static String TEST_FILE2 = "test2.xml";

	
	public static void main(String[] args)
	{
		try
		{
			InputStream in1 = new FileInputStream(new File(TEST_FILE));
			testElement = XMLTools.getDocument(in1);
			in1.close();

			InputStream in2 = new FileInputStream(new File(TEST_FILE2));
			test2Element = XMLTools.getDocument(in2);
			in2.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		System.out.println("name: "+ XMLTools.getString(testElement, "misc", "name"));
		System.out.println("age: "+ XMLTools.getInt(testElement, "misc", "age"));
		System.out.println("weigth: "+ XMLTools.getString(testElement, "misc", "weigth"));
		System.out.println("list: "+ XMLTools.getArrayString(testElement, "names"));
		System.out.println("fished: "+ XMLTools.getArrayString(test2Element, "animals", "fished"));
		
	}
}
