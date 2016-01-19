package BookStore.ejb.persistence;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-10-29T19:38:21")
@StaticMetamodel(Item.class)
public class Item_ { 

    public static volatile SingularAttribute<Item, String> category;
    public static volatile SingularAttribute<Item, String> itemID;
    public static volatile SingularAttribute<Item, String> shortDescription;
    public static volatile SingularAttribute<Item, String> longDescription;
    public static volatile SingularAttribute<Item, Double> cost;

}