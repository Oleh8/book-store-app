package bstore.bookstore.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Getter
@Setter
@SQLRestriction(value = "is_deleted=false")
@Table(name = "shopping_carts")
public class ShoppingCart {
    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id")
    private User user;

    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL)
    private Set<CartItem> cartItems = new HashSet<>();

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private boolean isDeleted = false;
}
