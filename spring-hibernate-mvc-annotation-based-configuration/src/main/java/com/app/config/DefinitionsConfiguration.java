package com.app.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.tiles.Attribute;
import org.apache.tiles.Definition;
import org.apache.tiles.definition.DefinitionsFactory;
import org.apache.tiles.request.Request;

public class DefinitionsConfiguration implements DefinitionsFactory {

	public static final Map<String, Definition> tilesDefinitions = new HashMap<String, Definition>();
	private static final Attribute TEMPLATE = new Attribute("/WEB-INF/views/templates/layout.jsp");

	@Override
	public Definition getDefinition(String name, Request tilesContext) {
		return tilesDefinitions.get(name);
	}

	private static void addDefaultLayoutDefninition(String defName, String body) {
		Map<String, Attribute> attributes = new HashMap<>();
		attributes.put("header", new Attribute("/WEB-INF/views/templates/header.jsp"));
		attributes.put("menu", new Attribute("/WEB-INF/views/templates/menu.jsp"));
		attributes.put("body", new Attribute(body));
		attributes.put("footer", new Attribute("/WEB-INF/views/templates/footer.jsp"));
		tilesDefinitions.put(defName, new Definition(defName, TEMPLATE, attributes));
	}

	public static void addDefinitions() {
		addDefaultLayoutDefninition("dashboard.tiles", "/WEB-INF/views/dashboard.jsp");
		addDefaultLayoutDefninition("about.tiles", "/WEB-INF/views/about.jsp");
		addDefaultLayoutDefninition("contact.tiles", "/WEB-INF/views/contact.jsp");
	}

}
