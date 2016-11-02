package org.xiyoulinux.idao;

import org.xiyoulinux.model.Address;

/**
 * Created by zhoupan on 10/31/16.
 */
public interface Iaddress {
    public boolean insert(Address address);

    public boolean delete(int address_id);

    public boolean update(Address address);

    public Address getAddressByID(int address_id);
}
