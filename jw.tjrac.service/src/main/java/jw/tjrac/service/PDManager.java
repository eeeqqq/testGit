package jw.tjrac.service;

import java.io.File;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;

import org.jbpm.api.ProcessDefinition;

public interface PDManager {
    public Collection<ProcessDefinition> getLasterVersions();

    public void deploy(File resource) throws Exception;

    public void deletePDKEY(String pdKEY);

    public InputStream showImage(String deploymentId);

    public List<String> selectAllProcessDefinitions();
}
