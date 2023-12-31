package no.hvl.dat108;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BmiController3 {
	
    private BmiCalculator bmiCalc = new BmiCalculator();
	
	@GetMapping("/bmi3")
	public String bmi5(@RequestParam(required = false) String hoyde, 
			@RequestParam(required = false) String vekt, Model model) {

        String hoydeInput = hoyde;
        String vektInput = vekt;

        if (hoydeInput != null && vektInput != null) {

            try {
                double hoydeMeter = Double.parseDouble(hoydeInput) / 100;
                double vektKg = Double.parseDouble(vektInput);

                if (bmiCalc.gyldigHoydeMeter(hoydeMeter) 
                        && bmiCalc.gyldigVektKg(vektKg)) {

                    double bmi = bmiCalc.beregnBmi(hoydeMeter, vektKg);
                    double avrundetBmi = bmiCalc.rundAvTilEnDesimal(bmi);
                    model.addAttribute("bmi", avrundetBmi);

                    BmiVektklasse vektklasse = bmiCalc.beregnVektklasse(bmi);
                    model.addAttribute("vektklasse", vektklasse);

                } else {
                	model.addAttribute("feilmelding", 
                			"Høyde og vekt må være normale verdier!");
                }
            } catch (NumberFormatException e) {
            	model.addAttribute("feilmelding", 
            			"Høyde og vekt må være tall!");
            }
        }

        return "bmi3";
	}
}
