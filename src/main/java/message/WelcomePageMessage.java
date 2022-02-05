package message;

import config.*;
import lombok.NonNull;

public class WelcomePageMessage extends Message {
    private static final DownloadRate NOT_EXIST_DOWNLOAD_RATE = null;
    private static final DownloadRate NOT_EXIST_RESTRICTED_FILE_EXTENSION = null;

    private final DownloadConfig downloadConfig;
    private final EntryPointConfig entryPointConfig;
    private final ThreadConfig threadConfig;

    public WelcomePageMessage(@NonNull DownloadConfig downloadConfig,
                              @NonNull EntryPointConfig entryPointConfig,
                              @NonNull ThreadConfig threadConfig) {
        this.downloadConfig = downloadConfig;
        this.entryPointConfig = entryPointConfig;
        this.threadConfig = threadConfig;
    }

    @Override
    public String create() {
        content.append("Server port : ").append(entryPointConfig.getPort()).append("</br>");
        content.append("welcome page path : ").append(entryPointConfig.getWelcomePagePath()).append("</br>");

        content.append("usable thread count : ").append(threadConfig.getUsableThreadCount()).append("</br>");
        content.append("waitable thread count : ").append(threadConfig.getWaitableThreadCount()).append("</br>");

        content.append("download path : ").append(downloadConfig.getDownloadPath()).append("</br>");
        content.append("download count : ").append(downloadConfig.getDownloadRate().getCount()).append("</br>");
        content.append("download period : ").append(downloadConfig.getDownloadRate().getPeriod()).append("</br>");
        content.append("restricted file extension : ").append(downloadConfig.getRestrictedFileExtension()).append("</br>");

        content.append("Each ip config").append("</br>");
        for (DownloadInfoAtIp downloadInfoAtIp : downloadConfig.getDownloadInfoAtIps()) {
            if (downloadInfoAtIp.getDownloadRate() == NOT_EXIST_DOWNLOAD_RATE
                    && downloadInfoAtIp.getRestrictedFileExtension() == NOT_EXIST_RESTRICTED_FILE_EXTENSION) {
                continue;
            }

            content.append("DownloadInfoAtIp : ").append(downloadInfoAtIp.getIp()).append("</br>");
            if (downloadInfoAtIp.getDownloadRate() != NOT_EXIST_DOWNLOAD_RATE) {
                content.append("download count : ").append(downloadInfoAtIp.getDownloadRate().getCount()).append("</br>");
                content.append("download period : ").append(downloadInfoAtIp.getDownloadRate().getPeriod()).append("</br>");
            }
            if (downloadInfoAtIp.getRestrictedFileExtension() != NOT_EXIST_RESTRICTED_FILE_EXTENSION) {
                content.append("extension : ").append(downloadInfoAtIp.getRestrictedFileExtension()).append("</br>");
            }
            content.append("</br>");
        }

        return content.toString();
    }
}
