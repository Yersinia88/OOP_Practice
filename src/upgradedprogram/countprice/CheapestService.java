package upgradedprogram.countprice;

import upgradedprogram.mobileservices.Decens;
import upgradedprogram.mobileservices.Fecsegteto;
import upgradedprogram.mobileservices.Lukracio;
import upgradedprogram.mobileservices.MobileServiceTypes;
import upgradedprogram.userinput.DurationOfTheCall;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;


public class CheapestService {
    private MobileServiceTypes cheapestPriceService;
    private int cheapestPrice;
    private DurationOfTheCall durationOfTheCall;

    public CheapestService(DurationOfTheCall durationOfTheCall) {
        this.durationOfTheCall = durationOfTheCall;
        theCheapestService();
    }

    private void theCheapestService() { //megnézi, hogy melyik lenne a legolcsóbb szolgáltató

        Map<MobileServiceTypes, Integer> cheapestService = new TreeMap<>();
        cheapestService.put(MobileServiceTypes.DECENS, new Decens(durationOfTheCall).getPriceOfTheCall());
        cheapestService.put(MobileServiceTypes.FECSEGTETO, new Fecsegteto(durationOfTheCall).getPriceOfTheCall());
        cheapestService.put(MobileServiceTypes.LUKRACIO, new Lukracio(durationOfTheCall).getPriceOfTheCall());

        Set<MobileServiceTypes> mobileServices = cheapestService.keySet();


        int min = cheapestService.get(MobileServiceTypes.DECENS);

        for (MobileServiceTypes mobilService : mobileServices) {
            if (cheapestService.get(mobilService) < min) {
                min = cheapestService.get(mobilService);
            }
        }

        for (MobileServiceTypes mobilService : mobileServices) {
            if (cheapestService.get(mobilService) == min) {
                cheapestPrice = min;
                cheapestPriceService = mobilService;
            }
        }
    }

    public MobileServiceTypes getCheapestPriceService() {
        return cheapestPriceService;
    }

    public int getCheapestPrice() {
        return cheapestPrice;
    }
}
