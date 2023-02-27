package jpabook.jpashop.domain.item;

import lombok.Getter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@DiscriminatorValue("Movie")
public class Movie  extends Item {
    private String director;
    private String actor;
}
