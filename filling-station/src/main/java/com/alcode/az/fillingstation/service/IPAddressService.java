package com.alcode.az.fillingstation.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class IPAddressService {

    /**
     * Get the local IP address of the machine.
     * @return Local IP address as a String.
     */
    public static String getLocalIPAddress() {
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            return localHost.getHostAddress(); // Returns local machine's IP address
        } catch (UnknownHostException e) {
            return "Error: Unable to determine local IP address.";
        }
    }
    public static String getLocalHostName() {
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            return localHost.getHostName(); // Returns local machine's IP address
        } catch (UnknownHostException e) {
            return "Error: Unable to determine local Host Name.";
        }
    }

    /**
     * Get all network interfaces and their IP addresses (e.g., Wi-Fi, Ethernet, VPN).
     * @return List of IP addresses for each network interface.
     */
    public static List<String> getAllNetworkInterfaces() {
        List<String> ipAddresses = new ArrayList<>();
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress inetAddress = addresses.nextElement();
                    if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                        ipAddresses.add(networkInterface.getDisplayName() + " - " + inetAddress.getHostAddress());
                    }
                }
            }
        } catch (SocketException e) {
            ipAddresses.add("Error: Unable to retrieve network interfaces.");
        }
        return ipAddresses;
    }

    /**
     * Scan local network for active devices (connected phones, other PCs).
     * @return List of active devices found on the local network.
     */
    public static List<String> scanLocalNetwork(String subnet) {
        List<String> activeDevices = new ArrayList<>();
        int timeout = 100; // Ping timeout in milliseconds

        for (int i = 1; i < 255; i++) { // Scanning range (192.168.1.1 - 192.168.1.254)
            String host = subnet + "." + i;
            try {
                InetAddress address = InetAddress.getByName(host);
                if (address.isReachable(timeout)) {
                    activeDevices.add("Device found: " + host + " - " + address.getHostName());
                }
            } catch (Exception ignored) {}
        }

        if (activeDevices.isEmpty()) {
            activeDevices.add("No active devices found.");
        }
        return activeDevices;
    }

    /**
     * Get the public IP address (WAN IP) using an external API.
     * @return Public IP address as a String.
     */
    public static String getPublicIPAddress() {
        try {
            URL url = new URL("https://api64.ipify.org"); // External API to get public IP
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String publicIP = reader.readLine();
            reader.close();

            return publicIP;
        } catch (Exception e) {
            return "Error: Unable to fetch public IP.";
        }
    }
    public static StringBuilder getIpLocationService() {
        String ip = getPublicIPAddress(); // Example public IP

        try {
            URL url = new URL("http://ip-api.com/json/" + ip);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Main method to test the class functionality.
     */
    public static void main(String[] args) {
        System.out.println("Local IP Address: " + getLocalIPAddress());
        System.out.println("Local Host Name: " + getLocalHostName());

        System.out.println("\nAll Network Interfaces:");
        getAllNetworkInterfaces().forEach(System.out::println);

        System.out.println("\nScanning Local Network (192.168.1.211)...");
        //scanLocalNetwork("192.168.1").forEach(System.out::println);

        System.out.println("\nPublic IP Address: " + getPublicIPAddress() + "\n\n");

        System.out.println("\n\nPublic IP Address: " + getIpLocationService() );
    }
}

