package pk.furniturestorebackend.chairs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pk.furniturestorebackend.database.furniture.chairs.Chair;
import pk.furniturestorebackend.database.furniture.chairs.ChairRepository;
import pk.furniturestorebackend.exceptions.NotFoundException;

@Service
public class ChairService {
    private static final int PAGE_RESULT_SIZE = 30;
    private final ChairRepository chairRepository;

    @Autowired
    public ChairService(ChairRepository chairRepository) {
        this.chairRepository = chairRepository;
    }

    public Chair getChair(Integer id) {
        if (id < 0)
            throw new IllegalArgumentException("Id can not be less than 0");
        return chairRepository.findById(id).orElseThrow(() -> new NotFoundException("Chair with id " + id + " not found"));
    }
}
