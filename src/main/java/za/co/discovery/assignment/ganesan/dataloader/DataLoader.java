package za.co.discovery.assignment.ganesan.dataloader;

import javafx.util.Pair;
import lombok.extern.log4j.Log4j2;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import za.co.discovery.assignment.ganesan.dto.PlanetDto;
import za.co.discovery.assignment.ganesan.dto.PlanetRouteDto;
import za.co.discovery.assignment.ganesan.service.PlanetRouteService;
import za.co.discovery.assignment.ganesan.service.PlanetService;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Configuration
@Log4j2
public class DataLoader {

    private final PlanetService planetService;
    private final PlanetRouteService planetRouteService;

    public DataLoader(PlanetService planetService,
                      PlanetRouteService planetRouteService) {

        this.planetService = planetService;
        this.planetRouteService = planetRouteService;
    }


    @PostConstruct
    public void loadEntities() {
        Resource dataFile = new ClassPathResource("data/planetroutedetail.xlsx");
        List<PlanetDto> planets = read(dataFile, 0, s -> new PlanetDto(s.get(0).getStringCellValue(), s.get(1).getStringCellValue()));
        List<String> savedPlanets = planetService.saveAll(planets).stream().map(PlanetDto::getId).collect(Collectors.toList());

        List<PlanetRouteDto> planetRoutesWithDistance = read(dataFile, 1, s -> PlanetRouteDto
                .builder()
                .id(getLong(s.get(0)))
                .origin(getString(s.get(1)))
                .destination(getString(s.get(2)))
                .distance(getDouble(s.get(3)))
                .build());
        Map<Long, Double> traffic = read(dataFile, 2, s -> new Pair<>(getLong(s.get(0)), getDouble(s.get(3))))
                .stream()
                .collect(Collectors.toMap(Pair::getKey, Pair::getValue));

        List<PlanetRouteDto> merged = planetRoutesWithDistance.stream()
                .map(r -> r.withTraffic(traffic.get(r.getId())))
                .collect(Collectors.toList());
        List<PlanetRouteDto> missing = merged.stream()
                .filter(s -> !savedPlanets.contains(s.getOrigin()) || !savedPlanets.contains(s.getDestination()))
                .collect(Collectors.toList());

        log.error("Missing Planets {}", missing);

        List<PlanetRouteDto> filtered = merged.stream()
                .filter(s -> savedPlanets.contains(s.getOrigin()) && savedPlanets.contains(s.getDestination()))
                .collect(Collectors.toList());
        planetRouteService.saveAll(filtered);

    }

    private static <T> List<T> read(Resource resource,
                                    int sheetIndex,
                                    Function<List<Cell>, T> mapper) {
        try (InputStream is = resource.getInputStream()) {
            XSSFWorkbook workbook = new XSSFWorkbook(is);
            XSSFSheet firstSheet = workbook.getSheetAt(sheetIndex);
            return StreamSupport.stream(firstSheet.spliterator(), false)
                    .skip(1)
                    .map(r -> StreamSupport.stream(r.spliterator(), false).collect(Collectors.toList()))
                    .map(mapper)
                    .collect(Collectors.toList());
        } catch (IOException ex) {
            log.error(ex);
        }
        return Collections.emptyList();
    }

    private static Long getLong(Cell cell) {
        return Double.valueOf(cell.getNumericCellValue()).longValue();
    }

    private static Double getDouble(Cell cell) {
        return cell.getNumericCellValue();
    }

    private static String getString(Cell cell) {
        return cell.getStringCellValue();
    }


}
