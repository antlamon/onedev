package io.onedev.server.util;

import java.io.Serializable;

import javax.annotation.Nullable;

import io.onedev.commons.utils.StringUtils;
import io.onedev.server.OneDev;
import io.onedev.server.entitymanager.ProjectManager;
import io.onedev.server.model.Project;

public class ProjectAwareRevision implements Serializable {

	private static final long serialVersionUID = 1L;

	private final Project project;
	
	private final String revision;
	
	public ProjectAwareRevision(Project project, String revision) {
		this.project = project;
		this.revision = revision;
	}

	public Project getProject() {
		return project;
	}

	public String getRevision() {
		return revision;
	}

	@Nullable
	public static ProjectAwareRevision from(String revisionFQN) {
		String projectName = StringUtils.substringBefore(revisionFQN, ":");
		String revision = StringUtils.substringAfter(revisionFQN, ":");
		Project project = OneDev.getInstance(ProjectManager.class).find(projectName);
		if (project != null)
			return new ProjectAwareRevision(project, revision);
		else
			return null;
	}
	
}
