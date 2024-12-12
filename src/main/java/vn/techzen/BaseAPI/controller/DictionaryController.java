package vn.techzen.BaseAPI.controller;

import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/dictionary")
public class DictionaryController {

    private static final Map<String, String> dictionary = new HashMap<>(){{
        put("hello", "xin chào");
        put("world", "thế giới");
        put("football", "bóng đá");
        put("volleyball", "bóng chuyền");
        put("dictionary", "từ điển");
        put("lemon", "quả chanh");
        put("banana", "trái chuối");
        put("apple", "quả táo");
    }};

    @GetMapping("/search")
    @CrossOrigin(origins = "http://localhost:5173")
    public Map<String, String> search(@RequestParam String keyword) {
        Map<String, String> response = new HashMap<>();
        String result = dictionary.getOrDefault(keyword.toLowerCase(), null);

        if (result != null) {
            response.put("keyword", keyword);
            response.put("result", result);
        } else {
            response.put("keyword", keyword);
            response.put("result", "Không tìm thấy từ khóa.");
        }

        return response;
    }
}
