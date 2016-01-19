package persistence;

import java.sql.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-10-13T08:45:58")
@StaticMetamodel(Project.class)
public class Project_ { 

    public static volatile SingularAttribute<Project, Date> STARTDATE;
    public static volatile SingularAttribute<Project, String> PROJ_NAME;
    public static volatile SingularAttribute<Project, String> ID;
    public static volatile SingularAttribute<Project, String> STATUS;
    public static volatile SingularAttribute<Project, String> DURATION;

}