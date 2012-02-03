package thejavapowder;


import org.w3c.dom.*;

import java.io.*;
import java.util.Random;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.w3c.dom.Attr;
import org.xml.sax.SAXException;


class FileSaver {
    static Variables var = thejavapowder.TheJavaPowder.var;
    static Console console = thejavapowder.TheJavaPowder.console;

	boolean loaded = false;
	int[] pref = new int[3];

    public void savePref()
    {
        try {

		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();


		Document settings = docBuilder.newDocument();
		Element rootElement = settings.createElement("settings");
		settings.appendChild(rootElement);

		Element width = settings.createElement("width");
		width.appendChild(settings.createTextNode("" + var.Width));

		Element height = settings.createElement("height");
		height.appendChild(settings.createTextNode("" + var.Height));

        Element zoom = settings.createElement("zoom");
		zoom.appendChild(settings.createTextNode("" + var.winZoom));

            rootElement.appendChild(width);
            rootElement.appendChild(height);
            rootElement.appendChild(zoom);

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(settings);
		StreamResult result = new StreamResult(new File("jpsettings.xml"));

		transformer.transform(source, result);

	  } catch (ParserConfigurationException pce) {
		pce.printStackTrace();
	  } catch (TransformerException tfe) {
		tfe.printStackTrace();
	  }


    }
	public boolean hasPref()
	{
		boolean haspref = false;
		        try {
					File fXmlFile = new File("jpsettings.xml");
					DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
					Document doc = dBuilder.parse(fXmlFile);
					haspref = true;
		        }
		        catch(Exception e)
		        {
			       haspref = false;
		        }
		return haspref;
	}

    public int[] getPref()
    {
	    if(!hasPref())
	    {
		    savePref();
		    var.Width = 600;
		    var.Height = 400;
		    var.winZoom = 2;
	    }
	    if(loaded)
	    {
		    return pref;
	    }
        try {
            File fXmlFile = new File("jpsettings.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            NodeList set = doc.getElementsByTagName("settings");
		    Node setN = set.item(0);

            Element eElement = (Element) setN;
            Node nNode = set.item(0);
            if (setN.getNodeType() == Node.ELEMENT_NODE) {

             pref[0] = Integer.parseInt(getTagValue("width", eElement));
             pref[1] = Integer.parseInt(getTagValue("height", eElement));
             pref[2] = Integer.parseInt(getTagValue("zoom", eElement));
	         loaded = true;
             }

            } catch (Exception e) {
            }
	    return pref;
    }


    private static String getTagValue(String sTag, Element eElement) {
	NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();

        Node nValue = nlList.item(0);

	return nValue.getNodeValue();
  }

    /*The FileSaver class
      *Methods: boolean saveFile(), boolean readFile()
      *Fields that must be set before methods called:
      *filename - for saveFile
      *readFileName - for readFile
      *sizex - for both
      *sizey - for both
      */







    // File read and save code below
        static FileOutputStream outp;
        static FileInputStream inp;
        static Random randomSaveName = new Random(1337);
        static File file;
        static Writer writer;
        static String readFileName;
        static byte[] databuffer2;
        static byte[][] data;
        static int sizex, sizey;

        public static void SaveFile(String fileName) {
        try {
            console.printtxt("Saving...");

            file = new File("" + fileName + ".jps");


            writer = new BufferedWriter(new FileWriter(file));
            writer.write("javapowder-save\n");
           // writer.write("version:" + var.versionID + "\n");
            writer.write("map:\n");
            for (int x = 0; x < var.Height; x++) {
                for (int y = 0; y < var.Width; y++) {
                    writer.write((int) var.Map[x][y]);
                }
            }
            /*
            writer.write("vmap:\n");
            for (int y = 0; y < var.Height; y++) {
                for (int x = 0; x < var.Width; x++) {
                    writer.write((int) var.VMap[x][y]);
                }
            }
            writer.write("pmap:\n");
            for (int y = 0; y < var.Height; y++) {
                for (int x = 0; x < var.Width; x++) {
                    writer.write((int) var.PMap[x][y]);
                    }
            }*/

                console.printtxt("Write ended successfully");

        } catch (IOException exception) {
            console.printtxt("I/O Exception!" + exception.getMessage());
        } finally {
            try {
                if(writer != null)
                {
                    writer.close();
                }
            } catch (IOException e) {

                e.printStackTrace();
            }
            console.printtxt("Write operation ended.");
        }
        }


    public static void LoadFile(String fileName) {
        if (fileName.equals("") || fileName == null)
        {
            readFileName = "default";
        }
        else
        {
            readFileName = fileName;
        }

        File save = new File(readFileName + ".jps");

        try {
	  File file = new File("bob.jps");
	  FileInputStream fis = null;
	  BufferedInputStream bis = null;
	  DataInputStream dis = null;


	      fis = new FileInputStream(file);

	      bis = new BufferedInputStream(fis);
	      dis = new DataInputStream(bis);

          int leX = 0;
          int leY = 0;
          int leMaxX = var.Width;
          int leMaxY = var.Height;


            System.out.println(fis.read());
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } // Creates the input
        catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        /*
        // Temporarily holds the file data
        data = new byte[sizex][sizey]; // The finished save data

        try {
            inp.skip(3);
        } catch (IOException e) {

            e.printStackTrace();
        } // Skipping the header

        try {
            for (int i2 = 0; i2 < inp.available() - 3; i2++) // The read from the file to the temp data buffer
                databuffer2[i2] = (byte) inp.read();
        } catch (IOException e) {

            e.printStackTrace();
        } // The read call

        for (int i = 0; i < sizex; i++) // The re-arranger(from 1d to 2d) - code by Bala R from Stack Overflow.
            for (int j = 0; j < sizey; j++)
                data[i][j] = (byte) (databuffer2[(j * sizex) + i] - 127);

        return true;*/
    }
}
		
	
		