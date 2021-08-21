package pk.furniturestorebackend.database.furniture.chairs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChairService {
    private final ChairRepository chairRepository;

    @Autowired
    public ChairService(ChairRepository chairRepository) {
        this.chairRepository = chairRepository;
    }

    public List<Chair> getAllChairs() {
        return chairRepository.findAll();
    }
}
