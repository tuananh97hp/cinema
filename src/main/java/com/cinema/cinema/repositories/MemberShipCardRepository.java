package com.cinema.cinema.repositories;

import com.cinema.cinema.models.MembershipCard;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MemberShipCardRepository extends CrudRepository<MembershipCard, Integer> {

    default MembershipCard findByStringId(String id) {
        int parseCardId = 0;
        try {
            parseCardId = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            System.out.println(e.toString());
        }

        Optional<MembershipCard> optionalMembershipCard = this.findById(parseCardId);
        MembershipCard membershipCard = null;
        if (optionalMembershipCard.isPresent()) {
            membershipCard = optionalMembershipCard.get();
        }

        return membershipCard;
    }
}
