package com.ozguryazilim.libraryproject.DAO;

import lombok.Value;

@Value
public class BookDAO {

    String name;
    String subName;
    String seriesName;
    String authorName;
    String publisherName;
    Long isbn;
    String explanation;

}
