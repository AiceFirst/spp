package by.bsuir.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("admin")
@SecondaryTable(schema = "mydb", name = "employer_info", pkJoinColumns = {@PrimaryKeyJoinColumn})
public class Admin extends UsersEntity {


}
