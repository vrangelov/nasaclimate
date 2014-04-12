/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bg.va.nasaclimate.mbeans;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.event.map.PointSelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.primefaces.model.map.Polygon;

/**
 *
 * @author Vasil Angelov
 */
@ManagedBean(name = "mapBean")
@ViewScoped
public class MapMBean implements Serializable {
    
    private MapModel advancedModel;
    private Marker marker;

    public MapModel getAdvancedModel() {
        return advancedModel;
    }

    public void setAdvancedModel(MapModel advancedModel) {
        this.advancedModel = advancedModel;
    }

    public Marker getMarker() {
        return marker;
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }

    /**
     * Creates a new instance of MapMBean
     */
    public MapMBean() {
        advancedModel = new DefaultMapModel();
          
        //Shared coordinates
        LatLng coord1 = new LatLng(40.71434617048493, -74.00545120239258);
        LatLng coord2 = new LatLng(40.713955826286046, -74.72900390625);
        LatLng coord3 = new LatLng(40.36328834091582, -74.72900390625);
        LatLng coord4 = new LatLng(40.81380923056961, -73.01513671875); 
        
        //Icons and Data
        advancedModel.addOverlay(new Marker(coord1, "New York 1"));
        advancedModel.addOverlay(new Marker(coord2, "New York 2"));
        advancedModel.addOverlay(new Marker(coord4, "New York 3"));
        advancedModel.addOverlay(new Marker(coord3, "New York 4"));
        
        //Polygon
        Polygon polygon = new Polygon();
        polygon.getPaths().add(coord1);
        polygon.getPaths().add(coord2);
        polygon.getPaths().add(coord3);
        polygon.getPaths().add(coord4);
  
        polygon.setStrokeColor("#FF9900");
        polygon.setFillColor("#FF9900");
        polygon.setStrokeOpacity(0.7);
        polygon.setFillOpacity(0.7);
        
        advancedModel.addOverlay(polygon);
    }
    
    public void onPolygonSelect(OverlaySelectEvent event) {
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Polygon Selected", null));
    }
    
    public void onMarkerSelect(OverlaySelectEvent event) {
        marker = (Marker) event.getOverlay();
    }

    public void onPointSelect(PointSelectEvent event) {
        LatLng latlng = event.getLatLng();

        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Point Selected", "Lat:" + latlng.getLat() + ", Lng:" + latlng.getLng()));
        System.out.println("Lat:" + latlng.getLat() + ", Lng:" + latlng.getLng());
    }

    public void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
}
