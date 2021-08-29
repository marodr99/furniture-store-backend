package pk.furniturestorebackend.chairs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pk.furniturestorebackend.database.furniture.chairs.Chair;
import pk.furniturestorebackend.database.furniture.chairs.ChairRepository;

@Service
public class ChairService {
    private static final int PAGE_RESULT_SIZE = 30;
    private final ChairRepository chairRepository;

    @Autowired
    public ChairService(ChairRepository chairRepository) {
        this.chairRepository = chairRepository;
    }

    public Page<Chair> getAllChairs(Integer page) {
        if (page < 0)
            throw new IllegalArgumentException("Page number is incorrect");
        PageRequest pageRequest = PageRequest.of(page, PAGE_RESULT_SIZE);
        return chairRepository.findAll(pageRequest);
    }
}
