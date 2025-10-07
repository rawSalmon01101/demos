import simple.*;

class virusscanner
{
    public static void main(String[] args) 
    {
        String dir = input.line("Enter the folder to scan: ");
        win_cmd.run_once("\"C:\\Program Files\\Windows Defender\\MpCmdRun.exe\" -Scan -ScanType 3 -File %userprofile%\\" + dir);
    }
}