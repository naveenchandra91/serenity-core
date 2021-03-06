package serenitycore.net.thucydides.core.requirements;

import serenitymodel.net.thucydides.core.issues.IssueTracking;
import serenitymodel.net.thucydides.core.model.ReportType;
import serenitymodel.net.thucydides.core.reports.html.ReportNameProvider;
import serenitymodel.net.thucydides.core.requirements.Requirements;
import serenitymodel.net.thucydides.core.requirements.RequirementsProviderService;
import serenitymodel.net.thucydides.core.requirements.RequirementsService;
import serenitymodel.net.thucydides.core.requirements.reports.MultipleSourceRequirmentsOutcomeFactory;
import serenitymodel.net.thucydides.core.requirements.reports.RequirementsOutcomeFactory;

import java.util.List;

import static serenitymodel.net.thucydides.core.guice.Injectors.getInjector;
import static serenitymodel.net.thucydides.core.reports.html.ReportNameProvider.NO_CONTEXT;

/**
 * Created by john on 26/06/2016.
 */
public class RequirementsForOutcomeTypes implements Requirements {

    private final RequirementsService requirementsService;
    private final RequirementsOutcomeFactory requirmentsOutcomeFactory;

    public RequirementsForOutcomeTypes() {
        this.requirementsService = getInjector().getInstance(RequirementsService.class);
        this.requirmentsOutcomeFactory = new MultipleSourceRequirmentsOutcomeFactory(
                getInjector().getInstance(RequirementsProviderService.class).getRequirementsProviders(),
                getInjector().getInstance(IssueTracking.class),
                new ReportNameProvider(NO_CONTEXT, ReportType.HTML, getRequirementsService()));
    }

    public RequirementsForOutcomeTypes(ReportNameProvider reportNameProvider) {
        this.requirementsService = getInjector().getInstance(RequirementsService.class);
        this.requirmentsOutcomeFactory = new MultipleSourceRequirmentsOutcomeFactory(
                getInjector().getInstance(RequirementsProviderService.class).getRequirementsProviders(),
                getInjector().getInstance(IssueTracking.class),
                reportNameProvider);
    }

    public RequirementsService getRequirementsService() {
        return requirementsService;
    }

    @Override
    public RequirementsOutcomeFactory getRequirementsOutcomeFactory() {
        return requirmentsOutcomeFactory;
    }

    public List<String> getTypes() {
        return requirementsService.getRequirementTypes();
    }

}
