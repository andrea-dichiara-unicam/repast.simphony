package repast.simphony.systemdynamics.translator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class View {

    public static final String ARROW = "ARROW";
    public static final String VARIABLE = "VARIABLE";
    public static final String VALVE = "VALVE";
    public static final String COMMENT = "COMMENT";
    public static final String BITMAP = "BITMAP";
    public static final String METAFILE = "METAFILE";
    public static final String RATE = "RATE";

    private String name;
    private String versionCode;
    private String viewDefaultFont;
    private List<String> rawObjects;
    private List<GraphicObject> graphicObjects;
    private int currentPtr = 0;

    public View(String name, String versionCode, String viewDefaultFont) {
	this.name = name;
	this.versionCode = versionCode;
	this.viewDefaultFont = viewDefaultFont;
	graphicObjects = new ArrayList<GraphicObject>();

    }

    public void parse(SystemDynamicsObjectManager sdObjectManager) {

	while(currentPtr < rawObjects.size()) {
	    String raw = rawObjects.get(currentPtr++);
	    graphicObjects.add(new GraphicObject(sdObjectManager, this, raw));
	}
	sdObjectManager.extractStructure(this);
    }


    public String translateCodeToString(String numeric) {

	//	public static final String ARROW = "ARROW";
	//	public static final String VARIABLE = "VARIABLE";
	//	public static final String VALVE = "VALVE";
	//	public static final String COMMENT = "COMMENT";
	//	public static final String BITMAP = "BITMAP";
	//	public static final String METAFILE = "METAFILE";

	if (numeric.equals(GraphicObject.ARROW))
	    return ARROW;
	else if (numeric.equals(GraphicObject.VARIABLE))
	    return VARIABLE;
	else if (numeric.equals(GraphicObject.VALVE))
	    return VALVE;
	else if (numeric.equals(GraphicObject.COMMENT))
	    return COMMENT;
	else if (numeric.equals(GraphicObject.BITMAP))
	    return BITMAP;
	else if (numeric.equals(GraphicObject.METAFILE))
	    return METAFILE;
	else if (numeric.equals(GraphicObject.RATE))
	    return RATE;
	else
	    return "UNKNOWN";

    }
    
    public String peekNextRawObject() {
	// error condition?
	String next = rawObjects.get(currentPtr);
//	currentPtr++;
	return next;

    }

    public String getNextRawObject() {
	// error condition?
	String next = rawObjects.get(currentPtr);
	currentPtr++;
	return next;

    }

    public void print() {
	System.out.println("##########################");
	System.out.println("View Name: "+name);
	System.out.println("View Version: "+versionCode);
	System.out.println("View Default Font: "+viewDefaultFont);
	System.out.println("Graphic Objects:");
	for (GraphicObject go : graphicObjects)
	    go.print();
    }

    public List<String> getRawObjects() {
	return rawObjects;
    }

    public void setRawObjects(List<String> rawObjects) {
	this.rawObjects = rawObjects;
    }

    public String getName() {
	return name;
    }

    public List<GraphicObject> getGraphicObjects() {
	return graphicObjects;
    }

}
