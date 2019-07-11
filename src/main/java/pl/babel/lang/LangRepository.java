package pl.babel.lang;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface LangRepository extends JpaRepository<Lang, Integer> {

}
