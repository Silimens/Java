package reports;

import beans.model.Task;
import beans.model.Track;
import beans.model.User;
import beans.model.dao.TaskDAO;
import beans.model.dao.TrackDAO;
import beans.model.dao.UserDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import reports.base.Report;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AllUsersReport implements Report {
    private static final Log log = LogFactory.getLog(AllUsersReport.class);
    private static SimpleDateFormat defaultSimpleDateFormat = new SimpleDateFormat("HH:mm:ss.SSS dd-MM-yyyy");

    private UserDAO userDAO;
    private TaskDAO taskDAO;
    private TrackDAO trackDAO;

    @Override
    public String getReportStylesheet() {
        StringBuilder result = new StringBuilder();

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("templates/all_users_report.xsl")));
            String line;
            while ((line = reader.readLine()) != null)
                result.append(line);
        } catch (FileNotFoundException e) {
            log.error(e);
        } catch (IOException e) {
            log.error(e);
        }

        return result.toString();
    }

    @Override
    public Document getReportData() {
        Document document = null;
        try {
            document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

            Element root = document.createElement("REPORT");

            Element userElements = document.createElement("USERS");
            for (User user : userDAO.getAll()) {
                Element userElement = document.createElement("USER");
                userElement.setAttribute("id", String.valueOf(user.getId()));
                userElement.setAttribute("fullName", user.getFullName());
                userElement.setAttribute("login", user.getLogin());

                long overallTimeForUser = 0;
                Element taskElements = document.createElement("TASKS");
                for (Task task : userDAO.getUserTasks(user.getId())) {
                    Element taskElement = document.createElement("TASK");
                    taskElement.setAttribute("name", task.getName());

                    long overallTimeForTask = 0;
                    Element tracksElements = document.createElement("TRACKS");
                    for (Track track : trackDAO.getUserTaskTracks(user.getId(), task.getId())) {
                        Element trackElement = document.createElement("TRACK");
                        trackElement.setAttribute("id", String.valueOf(track.getId()));
                        trackElement.setAttribute("begin", track.getFormattedBegin());
                        trackElement.setAttribute("end", track.getFormattedEnd());
                        trackElement.setAttribute("comment", track.getComment());
                        tracksElements.appendChild(trackElement);

                        overallTimeForTask += track.getEnd().getTime() - track.getBegin().getTime();
                    }
                    taskElement.setAttribute("hours",String.valueOf(overallTimeForTask / (60 * 60 * 1000)));
                    taskElement.setAttribute("minutes",String.valueOf((overallTimeForTask / (60 * 1000)) % 60));
                    taskElement.setAttribute("seconds",String.valueOf((overallTimeForTask / 1000) % 60));

                    taskElement.appendChild(tracksElements);
                    taskElements.appendChild(taskElement);
                    overallTimeForUser += overallTimeForTask;
                }
                userElement.setAttribute("hours",String.valueOf(overallTimeForUser / (60 * 60 * 1000)));
                userElement.setAttribute("minutes",String.valueOf((overallTimeForUser / (60 * 1000)) % 60));
                userElement.setAttribute("seconds",String.valueOf((overallTimeForUser / 1000) % 60));

                userElement.appendChild(taskElements);
                userElements.appendChild(userElement);
            }
            root.appendChild(userElements);
            document.appendChild(root);
        } catch (ParserConfigurationException e) {
            log.error(e);
        }

        return document;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void setTaskDAO(TaskDAO taskDAO) {
        this.taskDAO = taskDAO;
    }

    public void setTrackDAO(TrackDAO trackDAO) {
        this.trackDAO = trackDAO;
    }
}
