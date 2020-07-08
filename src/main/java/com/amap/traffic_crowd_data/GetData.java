package com.amap.traffic_crowd_data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class GetData {
    public static String cityDataUrl = "https://report.amap.com/ajax/getCityInfo.do";
    public static String crowdDataUrl = "https://report.amap.com/ajax/cityDailyQuarterly.do?cityCode=cciittyy&year=yyeeaarr&quarter=qquuaarrtteerr";
    public static String crowdDataSql = "INSERT INTO t_crowd_data (city_code,crowd,time) VALUES (?,?,?);";
    public static String cityDataSql = "INSERT INTO t_city VALUES (?,?,?);";
    public static HikariConfig config = new HikariConfig();
    public static DataSource dataSource;
    public static JdbcTemplate template;
    static {
        config.setJdbcUrl("jdbc:mysql://localhost:3306/city_crowd_data?serverTimezone=GMT%2B8");
        config.setUsername("root");
        config.setPassword("1234");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setLeakDetectionThreshold(60 * 1000);
        dataSource = new HikariDataSource(config);
        template = new JdbcTemplate(dataSource);
    }

    public static void main(String[] args) throws IOException {
        getCrowdData();
    }

    public static void getCrowdData() throws JsonProcessingException {
        // 1. 获取城市列表
        RestTemplate CityRestTemplate = new RestTemplate();
        String recCityDataStr = CityRestTemplate.getForObject(cityDataUrl, String.class);
        // 接口返回数据中起始为\r\n，会导致直接解析失败
        recCityDataStr = recCityDataStr.replaceAll("\r\n", "");
        ObjectMapper mapper = new ObjectMapper();
        List<CityData> cityData = mapper.readValue(recCityDataStr, new TypeReference<List<CityData>>() {
        });
        // 接口中应该会返回101座城市
        System.out.println("共有城市：" + cityData.size());

        // 开多线程直接怼
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10, 0L, TimeUnit.SECONDS, new LinkedBlockingDeque<>());

        // 2. 遍历城市列表获取其中每个城市的数据
        long start = new Date().getTime();
        for (CityData city : cityData) {
            // 拼接口
            String targetStr = crowdDataUrl.replaceAll("cciittyy", city.getCode() + "");
            ArrayList<String> targetDateList = new ArrayList<>();
            targetDateList.add("2019-4");

            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int categories = (int) Math.ceil((calendar.get(Calendar.MONTH) + 1) / 3.0);
            for (int i = 2020; i <= year; i++) {
                if (calendar.get(Calendar.DAY_OF_YEAR) != 1) {
                    if (year != i) {
                        for (int j = 1; j <= 4; j++) {
                            targetDateList.add(i + "-" + j);
                        }
                    } else {
                        if ((calendar.get(Calendar.DAY_OF_MONTH) == 1 && (calendar.get(Calendar.MONTH) + 1 == 4 || calendar.get(Calendar.MONTH) + 1 == 7 || calendar.get(Calendar.MONTH) + 1 == 10))) {
                            for (int j = 1; j <= categories - 1; j++) {
                                targetDateList.add(i + "-" + j);
                            }
                        } else {
                            for (int j = 1; j <= categories; j++) {
                                targetDateList.add(i + "-" + j);
                            }
                        }
                    }
                } else {
                    if (year != i) {
                        for (int j = 1; j <= 4; j++) {
                            targetDateList.add(i + "-" + j);
                        }
                    } else {
                        break;
                    }
                }
            }
            String[] targetDates = targetDateList.toArray(new String[]{});
            System.out.println(targetDates);
            for (String targetDateStr : targetDates) {
                executor.execute(getAndSave(targetDateStr, targetStr, mapper, city));
            }
        }

        long end = new Date().getTime();
        System.out.println("共耗时：" + (end - start) / 1000 + "s");

    }

    public static Runnable getAndSave(String targetDateStr, String targetStr, ObjectMapper mapper, CityData city) {
        return () -> {
            String[] date = targetDateStr.split("-");
            RestTemplate crowdRestTemplate = new RestTemplate();
            String _1targetStr = targetStr;
            _1targetStr = _1targetStr.replaceAll("yyeeaarr", date[0]).replaceAll("qquuaarrtteerr", date[1]);
            String recCrowdDataStr = crowdRestTemplate.getForObject(_1targetStr, String.class);
            recCrowdDataStr = recCrowdDataStr.replaceAll("\r\n", "");
            ReceivedData receivedCrowdData = null;
            try {
                receivedCrowdData = mapper.readValue(recCrowdDataStr, ReceivedData.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            int length = receivedCrowdData.getCategories().length;
            System.out.println(city.getName() + "||" + targetDateStr + "：共" + length + " 天的数据");
            for (int i = 0; i < length; i++) {
                template.update(crowdDataSql, city.getCode(), receivedCrowdData.getSerieData()[i], receivedCrowdData.getCategories()[i]);
            }

        };
    }

    /**
     * 从cityDataUrl接口获取城市数据，并保存
     *
     * @throws IOException
     */
    public static void saveCityData() throws IOException {

        RestTemplate restTemplate = new RestTemplate();
        String url = "https://report.amap.com/ajax/getCityInfo.do";
        String recDataStr = restTemplate.getForObject(url, String.class);
        System.out.println(recDataStr);
        recDataStr = recDataStr.replaceAll("\r\n", "");
        ObjectMapper mapper = new ObjectMapper();
        List<CityData> cityData = mapper.readValue(recDataStr, new TypeReference<List<CityData>>() {
        });
        // 获取连接池对象
        DataSource dataSource = new HikariDataSource(config);
        JdbcTemplate template = new JdbcTemplate(dataSource);
        for (CityData city : cityData) {
            template.update(cityDataSql, city.getCode(), city.getName(), city.getPinyin());
        }
    }
}
