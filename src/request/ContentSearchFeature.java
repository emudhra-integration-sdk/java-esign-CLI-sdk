/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package request;

import com.emudhra.esign.ContentSearch;

/**
 *
 * @author 21701
 */
public class ContentSearchFeature {

    private String searchText;
    private ContentSearch.Position position;
    private String offset;
    private int width;
    private int height;

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public ContentSearch.Position getPosition() {
        return position;
    }

    public void setPosition(ContentSearch.Position position) {
        this.position = position;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
