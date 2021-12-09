package com.fastcampus.java.ifs;

import com.fastcampus.java.model.network.Header;

public interface CrudInterface {

    Header create();

    Header read(Long id);

    Header update();

    Header delete(Long id);
}
